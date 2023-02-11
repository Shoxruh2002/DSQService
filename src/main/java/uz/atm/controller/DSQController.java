package uz.atm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.atm.dto.DataDto;
import uz.atm.dto.FinancialReportFormDto;
import uz.atm.entity.PurchaseInfo;
import uz.atm.entity.financialReportFormOne.FinancialReportFormOne;
import uz.atm.entity.financialReportFormTwo.FinancialReportFormTwo;
import uz.atm.entity.individualInfoResponse.IndividualInfo;
import uz.atm.entity.juridicInfo.JuridicInfo;
import uz.atm.entity.juridicInfo.faunders.Founders;
import uz.atm.service.DSQService;
import uz.atm.service.JuridicInfoService;

import javax.validation.Valid;
import java.util.List;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 03/10/22
 * Time: 10:01
 */
@RestController
@RequestMapping( "/v1/atm/dsq" )
@RequiredArgsConstructor
public class DSQController {

    private final DSQService dsqService;
    private final JuridicInfoService juridicInfoService;

    @GetMapping( "/getFounders" )
    public ResponseEntity<DataDto<List<Founders>>> getFounders(@RequestParam Long tin) {
        DataDto<List<Founders>> founders = juridicInfoService.getFounders(tin.toString());
        return new ResponseEntity<>(founders, HttpStatus.OK);
    }

    @GetMapping( "/getJuridicEntityInfo" )
    public ResponseEntity<DataDto<JuridicInfo>> getJuridicEntityInfo(@RequestParam( "inn" ) Long inn) {
        DataDto<JuridicInfo> juridicEntityInfo = juridicInfoService.getJuridicEntityInfo(inn);
        return new ResponseEntity<>(juridicEntityInfo, HttpStatus.OK);
    }

    @GetMapping( "/getIndividualInfo" )
    public ResponseEntity<DataDto<IndividualInfo>> getIndividualInfo(@RequestParam( "pinfl" ) Long pnfl) {
        DataDto<IndividualInfo> individualInfoResponse = dsqService.getIndividualInfo(pnfl);
        return new ResponseEntity<>(individualInfoResponse, HttpStatus.OK);
    }

    @PostMapping( "/getFinancialReportForm1" )
    public ResponseEntity<DataDto<FinancialReportFormOne>> getFinancialForm1(@Valid @RequestBody FinancialReportFormDto dto) {
        DataDto<FinancialReportFormOne> financialForm1 = dsqService.getFinancialForm1(dto);
        return new ResponseEntity<>(financialForm1, HttpStatus.OK);
    }

    @PostMapping( "/getFinancialReportForm2" )
    public ResponseEntity<DataDto<FinancialReportFormTwo>> getFinancialForm2(@Valid @RequestBody FinancialReportFormDto dto) {
        DataDto<FinancialReportFormTwo> financialForm1 = dsqService.getFinancialForm2(dto);
        return new ResponseEntity<>(financialForm1, HttpStatus.OK);
    }

    @PostMapping( "/sendPurchaseInfo" )
    public ResponseEntity<DataDto<PurchaseInfo>> sendPurchaseInfo(@Valid @RequestBody PurchaseInfo dto) {
        DataDto<PurchaseInfo> purchaseInfo = dsqService.sendPurchaseInfo(dto);
        return new ResponseEntity<>(purchaseInfo, HttpStatus.OK);
    }

}
