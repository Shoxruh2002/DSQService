package uz.atm.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import uz.atm.service.GeneralService;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 2/9/23 4:54 PM
 **/
@Slf4j
@Service
public class RabbitConsumer {

    private final GeneralService generalService;

    public RabbitConsumer(GeneralService generalService) {
        this.generalService = generalService;
    }

    @RabbitListener( containerFactory = "rabbit-listener-container-factory-prod", queues = {"${queue.name.dsq}"} )
    public void receiveCportalTaxInProd(JsonNode node) {
        new Thread(() -> {
            try {
                generalService.parseDsqMethods(node, "prod");
            } catch ( Exception e ) {
                log.error("Error:  Service : prod ; Queue -> cportal_tax_in;  Json ->  " + node.toPrettyString());
                log.error("Error:  Queue -> cportal_tax_in;  Consuming Node from : " + ExceptionUtils.getStackTrace(e));
            }
        }).start();
    }

    @RabbitListener( containerFactory = "rabbit-listener-container-factory-dev", queues = {"${queue.name.dsq}"} )
    public void receiveCportalTaxInDev(JsonNode node) {
        new Thread(() -> {
            try {
                generalService.parseDsqMethods(node, "dev");
            } catch ( Exception e ) {
                log.error("Error:Service : dev ; Queue -> cportal_tax_in;  Json ->  " + node.toPrettyString());
                log.error("Error:  Queue -> cportal_tax_in;  Consuming Node from : " + ExceptionUtils.getStackTrace(e));
            }
        }).start();
    }
}
