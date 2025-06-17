package ru.tellurian.fin_lit_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FinLit API")
                        .version("0.0.1")
                        .contact(new Contact()
                            .name("tellurian")
                            .email("victor.babaryckin@yandex.ru")
                            .url("https://github.com/tellurian/fin-lit-api"))
                ).addServersItem(new Server()
                        .description("FinLit API dev Server")
                        .url("http://localhost:8080/"));
    }

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("api")
                .packagesToScan("ru.tellurian.fin_lit_api.endpoint")
                .pathsToMatch("/api/**")
                .build();
    }

}
