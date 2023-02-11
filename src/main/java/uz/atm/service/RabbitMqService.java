package uz.atm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.atm.dto.etp.BaseResponse;
import uz.atm.properties.RabbitMQProperties;


/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/30/22 3:38 PM
 **/
@Service
@Slf4j
public class RabbitMqService {
    private final RabbitTemplate rabbitTemplateProd;
    private final RabbitTemplate rabbitTemplateDev;
    private final RabbitMQProperties rabbitMQProperties;
    private final ObjectMapper objectMapper;


    public RabbitMqService(@Qualifier( "rabbit-template-prod" ) RabbitTemplate rabbitTemplateProd, @Qualifier( "rabbit-template-dev" ) RabbitTemplate rabbitTemplateDev, RabbitMQProperties rabbitMQProperties, ObjectMapper objectMapper) {
        this.rabbitTemplateProd = rabbitTemplateProd;
        this.rabbitTemplateDev = rabbitTemplateDev;
        this.rabbitMQProperties = rabbitMQProperties;
        this.objectMapper = objectMapper;
    }

    public void sendToAllEtps(Object object, String type) {

        String fanoutExchange = rabbitMQProperties.getExchanges().getFanoutExchange();
        Message message;
        try {
            message = new Message(objectMapper.writeValueAsBytes(object));
        } catch ( JsonProcessingException e ) {
            log.error("Error occured while sending juridic info by rabbitTemplate; exchange : {}, routingKey : {}, object : {}", fanoutExchange, "", object.toString());
            throw new RuntimeException("Exception occurred while sending BaseResponse by RabbitTemplate");
        }

        if ( type.equals("prod") ) {
            rabbitTemplateProd.send(fanoutExchange, "", message);
        } else if ( type.equals("dev") ) {
            rabbitTemplateDev.send(fanoutExchange, "", message);
        }

    }

    public void sendEtp(BaseResponse dto, String type) {
        Integer etpId = dto.getEtpId();
        String topicExchange = rabbitMQProperties.getExchanges().getTopicExchange();
        RabbitMQProperties.RoutingKey routingKey = rabbitMQProperties.getRoutingKey();
        switch ( etpId ) {
            case 1 -> send(topicExchange, routingKey.getUzEx(), dto, type);
            case 2 -> send(topicExchange, routingKey.getXtXarid(), dto, type);
            case 3 -> send(topicExchange, routingKey.getCoOperation(), dto, type);
            case 4 -> send(topicExchange, routingKey.getShaffof(), dto, type);
        }

    }


    private void send(String exchange, String routingKey, Object dto, String type) {
        Message message;
        try {
            message = new Message(objectMapper.writeValueAsBytes(dto));
        } catch ( JsonProcessingException e ) {
            log.error("Error occured while sending juridic info by rabbitTemplate; exchange : {}, routingKey : {}, object : {}", exchange, routingKey, dto.toString());
            throw new RuntimeException("Exception occurred while sending BaseResponse by RabbitTemplate");
        }

        if ( type.equals("prod") ) {
            rabbitTemplateProd.send(exchange, routingKey, message);
        } else if ( type.equals("dev") ) {
            rabbitTemplateDev.send(exchange, routingKey, message);
        }
    }
}
