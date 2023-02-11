package uz.atm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.atm.dto.DataDto;
import uz.atm.dto.TaxGapDto;
import uz.atm.dto.etp.JuridicInfoEtpRequest;
import uz.atm.dto.etp.TaxGapRequest;
import uz.atm.service.JuridicInfoService;
import uz.atm.service.TaxGapService;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 2/9/23 5:11 PM
 **/
@RestController
@RequestMapping( "/v1/atm/dsq/test" )
public class TestController {

    private final JuridicInfoService service;
    private final TaxGapService taxGapService;

    public TestController(JuridicInfoService service, TaxGapService taxGapService) {
        this.service = service;
        this.taxGapService = taxGapService;
    }

    @PostMapping( "/testJuridic" )
    public ResponseEntity<Boolean> getUpdate(@RequestBody JuridicInfoEtpRequest request) {
        return new ResponseEntity<>(service.sendJuridicInfoForEtp(request, "prod"), HttpStatus.OK);
    }

    @PostMapping( "/testTaxGap" )
    public ResponseEntity<DataDto<TaxGapDto>> getTaxGap(@RequestBody TaxGapRequest request) {
        return new ResponseEntity<>(taxGapService.sendTaxGapResponseForEtp(request, "prod"), HttpStatus.OK);
    }

}