package uz.atm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import uz.atm.properties.DSQApiProperties;
import uz.atm.properties.OpenApiProperties;
import uz.atm.properties.RabbitMQProperties;
import uz.atm.repository.FoundersRepository;
import uz.atm.repository.JuridicInfoRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

import static uz.atm.utils.AppUtils.longToBytes;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/28/22 11:30 AM
 **/
@Slf4j
@Configuration
@OpenAPIDefinition
@EnableRabbit
@EnableConfigurationProperties( value = {
        OpenApiProperties.class,
        DSQApiProperties.class,
        RabbitMQProperties.class} )
@EnableTransactionManagement
public class BaseConfig {

    private final DSQApiProperties dsqApiProperties;
    private final FoundersRepository foundersRepository;
    private final JuridicInfoRepository repository;
    @Value( "${request.id.file.path}" )
    private String filePath;

    public BaseConfig(DSQApiProperties dsqApiProperties, FoundersRepository foundersRepository, JuridicInfoRepository repository) {
        this.dsqApiProperties = dsqApiProperties;
        this.foundersRepository = foundersRepository;
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        if ( Files.notExists(Path.of(filePath)) ) {
            log.error("File Not Found with path : {}", filePath);
            throw new FileSystemNotFoundException("File Not Found with path : " + filePath);
        }
        try ( RandomAccessFile file = new RandomAccessFile(filePath, "rw");
              FileChannel channel = file.getChannel();
              FileLock lock = channel.lock() ) {
            file.seek(0);
            file.readLong();
        } catch ( IOException e ) {
            try ( RandomAccessFile file = new RandomAccessFile(filePath, "rw");
                  FileChannel channel = file.getChannel();
                  FileLock lock = channel.lock() ) {
                file.seek(0);
                file.write(longToBytes(500_000));
            } catch ( IOException ee ) {
                log.error("Exception occurred while generating requestId ; Cause : {}", ee.getMessage());
            }
            log.error("Exception occurred while generating requestId ; Cause : {}", e.getMessage());
        }
    }


    @Bean
    public void run() throws Exception {
        CommandLineRunner runner = (a) -> {
//            foundersRepository.deleteAllByCompanyTin(200000034L);
        };
        runner.run("s", "b"
        );
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean( "dsq-webclient-minfin" )
    WebClient webClient() {
        return WebClient.builder()
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                        .maxInMemorySize(1000 * 1024))
                .baseUrl(dsqApiProperties.getUrl().getProtocol() + dsqApiProperties.getUrl().getDomain())
                .build();

    }


}
