package com.altice.alticcisequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {    

    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.altice.alticcisequence.controller"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(metaData());                                 
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Alticci Sequence API")
                .description("Configuração SWAGGER para Alticci Sequence API.")
                .version("0.0.1")
                .license("Apache 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}