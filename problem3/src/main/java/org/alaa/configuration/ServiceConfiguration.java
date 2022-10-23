package org.alaa.configuration;




import org.alaa.infrastructure.domain.IPolishNotationInfrastructureRepository;
import org.alaa.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration
{

    @Autowired
    private IPolishNotationInfrastructureRepository polishNotationInfrastructureRepository;
    @Bean
    public Service getService(){
        return new Service(polishNotationInfrastructureRepository);
    }
}
