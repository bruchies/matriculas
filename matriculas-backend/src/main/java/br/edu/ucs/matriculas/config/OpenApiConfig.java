package br.edu.ucs.matriculas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("📚 API - Sistema de Matrículas no Ensino Superior")
                .version("1.0.0")
                .description("API desenvolvida para o projeto da disciplina **Projeto e Arquitetura de Software**.\n\nPermite consultar agregações sobre matrículas em cursos de graduação no Brasil entre 2014 e 2022.")
                .contact(new Contact()
                    .name("Felipe Paganin Castilhos")
                    .email("fpcastilhos@ucs.br")  
                    .url("https://github.com/felipepcastilhos/matriculas-backend")) 
            );
    }
}