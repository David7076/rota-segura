package br.com.fiap.rota_segura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("Rota Segura API")
                .version("v1")
                .description("API que sugere trajetos com menor exposição conhecida a alagamentos, "
                        + "enchentes e outros riscos provocados pela chuva (São Paulo)."));
    }
}
