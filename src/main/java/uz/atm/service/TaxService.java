package uz.atm.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.atm.entity.tax.SentTax;
import uz.atm.entity.tax.Tax;
import uz.atm.repository.SentTaxRepository;
import uz.atm.repository.TaxRepository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static uz.atm.enums.TaxStatus.IN;
import static uz.atm.enums.TaxStatus.OUT;

/**
 * Author : Khonimov Ulugbek
 * Date : 15.11.2022
 * Time : 12:49 PM
 */


@Service
@EnableScheduling
@Slf4j
public class TaxService extends AbstractService<TaxRepository> {
    @Value( value = "${tax.excel.url}" )
    private String url;
    private final RabbitMqService rabbitMqService;
    private final SentTaxRepository sentTaxRepository;

    public TaxService(TaxRepository repository, SentTaxRepository sentTaxRepository, RabbitMqService rabbitMqService) {
        super(repository);
        this.rabbitMqService = rabbitMqService;
        this.sentTaxRepository = sentTaxRepository;
    }

    @Scheduled( fixedRate = 24, timeUnit = TimeUnit.HOURS, initialDelay = 1 ) // 24 hours
    private void create() {
        List<Tax> all = repository.findAll();
        if ( ! all.isEmpty() ) {
            all.forEach(e -> repository.updateStatus(OUT, e.getId()));
        }
        try ( InputStream is = new URL(url).openStream() ) {  //try catch with resources
            var bytes = is.readAllBytes();
            ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
            var workbook = new HSSFWorkbook(bin);
            var sheet = workbook.getSheetAt(0);

            var last = sheet.getLastRowNum();
            for ( int i = 5; i < last; i++ ) {
                try {
                    var r = sheet.getRow(i);
                    var address = r.getCell(1).getStringCellValue();
                    var stir = r.getCell(2).getStringCellValue();
                    var name = r.getCell(3).getStringCellValue();
                    var registrationDate = r.getCell(4).getStringCellValue();
                    var founder = r.getCell(5).getStringCellValue();
                    String pattern = "dd.MM.yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    Date date = simpleDateFormat.parse(registrationDate);
                    var e = repository.findByStir(Long.valueOf(stir));
                    if ( e.isPresent() ) {
                        repository.updateStatus(IN, e.get().getId());
                    } else {
                        Tax entity = Tax
                                .builder()
                                .address(address)
                                .stir(Long.valueOf(stir))
                                .name(name)
                                .registrationDate(date)
                                .status(IN)
                                .isSent(false)
                                .founder(founder)
                                .build();
                        repository.save(entity);
                    }
                } catch ( Exception e ) {
                    log.error(e.getMessage());
                }
            }
            repository.findAll().forEach(taxEntity -> {
                if ( taxEntity.getStatus().equals(OUT) ) {
                    repository.updateSent(false, taxEntity.getId());
                }
                sendToRabbit(taxEntity);
            });
        } catch ( Exception e ) {
            log.warn(e.getMessage());
        }
    }


    private void sendToRabbit(Tax tax) {
        var status = tax.getStatus().equals(IN);
        var sent = tax.getIsSent();
        if ( ! sent ) {
            rabbitMqService.sendToAllEtps(tax, "prod");
            rabbitMqService.sendToAllEtps(tax, "dev");
            repository.updateSent(true, tax.getId());
        }
        if ( ! status ) {
            var sentEntity = SentTax
                    .builder()
                    .stir(tax.getStir())
                    .name(tax.getName())
                    .founder(tax.getFounder())
                    .address(tax.getAddress())
                    .isSent(tax.getIsSent())
                    .registrationDate(tax.getRegistrationDate())
                    .status(tax.getStatus())
                    .build();
            sentTaxRepository.save(sentEntity);
            repository.delete(tax);
        }
    }

}




