package uz.atm.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Author : Khonimov Ulugbek
 * Date : 21.11.2022
 * Time : 6:09 PM
 */
@ConfigurationProperties("rabbit")
@Getter
@Setter
public class RabbitMQProperties {

    private Credentials dev;

    private Credentials prod;

    private Exchanges exchanges;

    private RoutingKey routingKey;

    @Data
    public static class Exchanges {

        private String fanoutExchange;
        private String topicExchange;
    }

    @Data
    public static class RoutingKey {

        private String xtXarid;
        private String uzEx;
        private String coOperation;
        private String shaffof;
    }

    @Data
    public static class Credentials {

        private String username;
        private String password;
        private Integer port;
        private String host;
    }

}




