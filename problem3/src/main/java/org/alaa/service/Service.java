package org.alaa.service;

import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import org.alaa.domain.PolishNotationDomain;
import org.alaa.domain.RequestSystem;
import org.alaa.domain.RequestUser;
import org.alaa.infrastructure.domain.IPolishNotationInfrastructureRepository;

import java.math.BigDecimal;

@AllArgsConstructor
public class Service
{

    IPolishNotationInfrastructureRepository polishNotationInfrastructureRepository;

    public Validation<String,BigDecimal> getPolishNotion(String transactionId, RequestUser requestUser, RequestSystem requestSystem, PolishNotationDomain polishNotationDomain)
    {
        return polishNotationInfrastructureRepository.getPolishNotation(transactionId,requestUser,requestSystem,polishNotationDomain);
    }
}
