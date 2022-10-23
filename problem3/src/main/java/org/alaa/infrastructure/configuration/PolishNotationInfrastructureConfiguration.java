package org.alaa.infrastructure.configuration;



import org.alaa.infrastructure.PolishNotationInfrastructureRepository;
import org.alaa.infrastructure.domain.IPolishNotationInfrastructureRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PolishNotationInfrastructureConfiguration
{
    @Bean
    public IPolishNotationInfrastructureRepository getIPolishNotationInfrastructureRepository(){
        return new PolishNotationInfrastructureRepository();
    }
}
