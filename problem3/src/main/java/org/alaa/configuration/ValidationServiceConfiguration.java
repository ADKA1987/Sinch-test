package org.alaa.configuration;


import org.alaa.service.ValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationServiceConfiguration
{

    @Bean
    public ValidationService getValidationService(){
        return new ValidationService();
    }
}
