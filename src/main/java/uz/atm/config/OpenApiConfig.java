package uz.atm.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.atm.properties.OpenApiProperties;


/**
 * Author: Bekpulatov Shoxruh
 * Date: 06/07/22
 * Time: 15:12
 */

@Configuration
@ConditionalOnProperty( name = "springdoc.swagger-ui.enabled", havingValue = "true", matchIfMissing = true )
public class OpenApiConfig {

    private final OpenApiProperties openApiProperties;

    @Autowired
    public OpenApiConfig(OpenApiProperties openApiProperties) {
        this.openApiProperties = openApiProperties;
    }

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(info());
    }

    private Info info() {
        return new Info()
                .title(openApiProperties.getTitle())
                .description(openApiProperties.getDescription())
                .version(openApiProperties.getVersion())
                .contact(new Contact()
                        .name(openApiProperties.getContactName())
                        .email(openApiProperties.getContactEmail())
                        .url(openApiProperties.getContactUrl()))
                .license(new License()
                        .name(openApiProperties.getLicenseName())
                        .url(openApiProperties.getLicenseUrl()));
    }
}
