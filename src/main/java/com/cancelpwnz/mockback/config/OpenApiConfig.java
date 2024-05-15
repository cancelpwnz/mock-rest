package com.cancelpwnz.mockback.config;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.util.PrimitiveType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String TITTLE = "OpenAPI для подсистемы СЭД";
    private static final String DESCRIPTION = "";
    private static final String LICENSE_TEXT = "License";
    private static final String VERSION = "1.0";

    @Bean
    public OpenAPI customOpenAPI() {
        PrimitiveType.customClasses().put("java.time.Instant", PrimitiveType.DATE_TIME);
        return new OpenAPI()
                .openapi("3.0.0")
                .info(new Info()
                        .title(TITTLE)
                        .version(VERSION)
                        .description(DESCRIPTION));
    }

}
