package uz.atm.config;


import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import uz.atm.properties.RabbitMQProperties;


/**
 * Author: Bekpulatov Shoxruh
 * Date: 25/01/23
 * Time: 15:17
 */

@Configuration
public class RabbitMQConfiguration {
    private final RabbitMQProperties rabbitMqProperties;

    public RabbitMQConfiguration(RabbitMQProperties rabbitMqProperties) {
        this.rabbitMqProperties = rabbitMqProperties;
    }

    @Bean( name = "connection-factory-dev" )
    public ConnectionFactory connectionFactoryDev() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        RabbitMQProperties.Credentials credentials = rabbitMqProperties.getDev();
        connectionFactory.setHost(credentials.getHost());
        connectionFactory.setPort(credentials.getPort());
        connectionFactory.setUsername(credentials.getUsername());
        connectionFactory.setPassword(credentials.getPassword());
        return connectionFactory;
    }

    @Bean( name = "connection-factory-prod" )
    @Primary
    public ConnectionFactory connectionFactoryProd() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        RabbitMQProperties.Credentials credentials = rabbitMqProperties.getProd();
        connectionFactory.setHost(credentials.getHost());
        connectionFactory.setPort(credentials.getPort());
        connectionFactory.setUsername(credentials.getUsername());
        connectionFactory.setPassword(credentials.getPassword());
        return connectionFactory;
    }


    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(rabbitMqProperties.getExchanges().getFanoutExchange());
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean( "rabbit-template-dev" )
    public RabbitTemplate rabbitTemplateDev(@Qualifier( "connection-factory-dev" ) ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Primary
    @Bean( "rabbit-template-prod" )
    public RabbitTemplate rabbitTemplateProd(@Qualifier( "connection-factory-prod" ) ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean( name = "rabbit-listener-container-factory-dev" )
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactoryDev(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier( "connection-factory-dev" ) ConnectionFactory connectionFactory) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setDefaultRequeueRejected(false);
        return factory;
    }

    @Bean( name = "rabbit-listener-container-factory-prod" )
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactoryProd(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier( "connection-factory-prod" ) ConnectionFactory connectionFactory) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setDefaultRequeueRejected(false);
        return factory;
    }


}




