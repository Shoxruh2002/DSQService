package uz.atm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.atm.dto.DataDto;
import uz.atm.dto.JuridicInfoUpdateDto;
import uz.atm.entity.juridicInfo.JuridicInfoUpdate;
import uz.atm.service.JuridicInfoUpdateService;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/26/22 5:49 PM
 **/
@RestController
@RequestMapping( "/v1/atm/dsq/juridic" )
public class JuridicInfoUpdateController {

    private final JuridicInfoUpdateService service;

    public JuridicInfoUpdateController(JuridicInfoUpdateService service) {
        this.service = service;
    }

    @PostMapping( "/update" )
    public ResponseEntity<DataDto<String>> getUpdate(@RequestBody JuridicInfoUpdateDto dto) {
        JuridicInfoUpdate save = service.save(dto);
        return new ResponseEntity<>(new DataDto<>("Updated successfully with tin : " + save.getData().getTin()), HttpStatus.OK);
    }

}