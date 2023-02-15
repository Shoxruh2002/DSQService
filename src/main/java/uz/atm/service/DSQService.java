package uz.atm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import uz.atm.dto.*;
import uz.atm.entity.financialReportFormOne.FinancialReportFormOne;
import uz.atm.entity.financialReportFormTwo.FinancialReportFormTwo;
import uz.atm.entity.individualInfoResponse.IndividualInfo;
import uz.atm.properties.DSQApiProperties;
import uz.atm.service.caller.DSQCaller;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 03/10/22
 * Time: 09:58
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DSQService {
    private final DSQCaller dsqCaller;
    private final DSQApiProperties dsqApiProperties;
    private final ObjectMapper objectMapper;

    public DataDto<IndividualInfo> getIndividualInfo(Long pinfl) {
        if ( pinfl.toString().length() != 14 )
            throw new RuntimeException("Pinfl must be contains 14 numbers");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("pinfl", pinfl.toString());
        String endpoint = dsqApiProperties.getUrl().getPath().getIndividualInfoAPI();
        DataDto<JsonNode> response = dsqCaller.getCall(params, endpoint, JsonNode.class);
        if ( ! response.success ) {
            dsqCaller.loginCall();
            response = dsqCaller.getCall(params, endpoint, JsonNode.class);
        }
        if ( response.success ) {
            JsonNode body = response.body;
            if ( body.get("success").asBoolean() ) {
                return new DataDto<>(new Gson().fromJson(body.get("data").toString(), IndividualInfo.class));
            } else return new DataDto<>(new AppErrorDto("Cause : " + body.get("reason"), HttpStatus.BAD_REQUEST));
        } else return new DataDto<>(response.error);
    }

    public DataDto<FinancialReportFormOne> getFinancialForm1(FinancialReportFormDto dto) {
        String endpoint = dsqApiProperties.getUrl().getPath().getFinancialStatementsFormOneAPI();
        DataDto<JsonNode> response = dsqCaller.postCall(dto, endpoint, JsonNode.class);
        if ( ! response.success ) {
            dsqCaller.loginCall();
            response = dsqCaller.postCall(dto, endpoint, JsonNode.class);
        }
        if ( response.success ) {
            JsonNode body = response.body;
            if ( body.get("success").asBoolean() ) {
                try {
                    return new DataDto<>(objectMapper.readValue(body.get("data").toString(), FinancialReportFormOne.class));
                } catch ( JsonProcessingException e ) {
                    return new DataDto<>(new AppErrorDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
                }
            } else return new DataDto<>(new AppErrorDto("Cause : " + body.get("reason"), HttpStatus.BAD_REQUEST));
        } else return new DataDto<>(response.error);
    }

    public DataDto<FinancialReportFormTwo> getFinancialForm2(FinancialReportFormDto dto) {
        String endpoint = dsqApiProperties.getUrl().getPath().getFinancialStatementsFormTwoAPI();
        DataDto<JsonNode> response = dsqCaller.postCall(dto, endpoint, JsonNode.class);
        if ( ! response.success ) {
            dsqCaller.loginCall();
            response = dsqCaller.postCall(dto, endpoint, JsonNode.class);
        }
        if ( response.success ) {
            JsonNode body = response.body;
            if ( body.get("success").asBoolean() ) {
                try {
                    return new DataDto<>(objectMapper.readValue(body.get("data").toString(), FinancialReportFormTwo.class));
                } catch ( JsonProcessingException e ) {
                    return new DataDto<>(new AppErrorDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
                }
            } else return new DataDto<>(new AppErrorDto("Cause : " + body.get("reason"), HttpStatus.BAD_REQUEST));
        } else return new DataDto<>(response.error);
    }


    public DataDto<PurchaseInfoDto> sendPurchaseInfo(PurchaseInfoDto dto) {
        String endpoint = dsqApiProperties.getUrl().getPath().getPurchaseInfoAPI();
        DataDto<JsonNode> response = dsqCaller.postCall(dto, endpoint, JsonNode.class);
        if ( ! response.success ) {
            dsqCaller.loginCall();
            response = dsqCaller.postCall(dto, endpoint, JsonNode.class);
        }
        if ( response.success ) {
            JsonNode body = response.body;
            if ( body.get("success").asBoolean() ) {
                try {
                    return new DataDto<>(objectMapper.readValue(body.get("data").toString(), PurchaseInfoDto.class));
                } catch ( JsonProcessingException e ) {
                    return new DataDto<>(new AppErrorDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
                }
            } else return new DataDto<>(new AppErrorDto("Cause : " + body.get("reason"), HttpStatus.BAD_REQUEST));
        } else return new DataDto<>(response.error);
    }

    public DataDto<StockTradingDto> sendStockTrading(StockTradingDto dto) {
        String endpoint = dsqApiProperties.getUrl().getPath().getStockTradingAPI();
        DataDto<JsonNode> response = dsqCaller.postCall(dto, endpoint, JsonNode.class);
        if ( ! response.success ) {
            dsqCaller.loginCall();
            response = dsqCaller.postCall(dto, endpoint, JsonNode.class);
        }
        if ( response.success ) {
            JsonNode body = response.body;
            if ( body.get("success").asBoolean() ) {
                return new DataDto<>(dto);
            } else return new DataDto<>(new AppErrorDto("Cause : " + body.get("message"), HttpStatus.BAD_REQUEST));
        } else return new DataDto<>(response.error);
    }
}
