package uz.atm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.atm.dto.etp.JuridicInfoEtpRequest;
import uz.atm.dto.etp.TaxGapRequest;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeneralService {
    private final RestTemplate restTemplate;
    private final TaxGapService taxGapService;
    private final JuridicInfoService juridicInfoService;

    public void parseDsqMethods(JsonNode node, String type) {

        String str = node.toString();
        String methodName;
        if ( node.has("METHOD_NAME") ) {
            methodName = node.get("METHOD_NAME").asText();
        } else if ( node.has("TYPE") ) {
            methodName = node.get("TYPE").asText();
        } else methodName = "DEFAULT";

        if ( methodName.equals("JURIDIC") ) {
            try {
                JuridicInfoEtpRequest dto = new ObjectMapper().readValue(str, new TypeReference<>() {
                });
                juridicInfoService.sendJuridicInfoForEtp(dto, type);
            } catch ( JsonProcessingException e ) {
                throw new RuntimeException(e);
            }
        } else if ( methodName.equals("TAX_GAP") ) {
            try {
                TaxGapRequest dto = new ObjectMapper().readValue(str, new TypeReference<>() {
                });
                taxGapService.sendTaxGapResponseForEtp(dto, type);
            } catch ( JsonProcessingException e ) {
                throw new RuntimeException(e);
            }
        }

    }

}
