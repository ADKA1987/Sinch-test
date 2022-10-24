package org.alaa.service;

import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import org.alaa.domain.ErrorResponse;
import org.alaa.domain.PolishNotationDomain;
import org.alaa.domain.RequestSystem;
import org.alaa.domain.RequestUser;
import org.alaa.infrastructure.domain.IPolishNotationInfrastructureRepository;

import java.math.BigDecimal;

@AllArgsConstructor
public class Service
{

    IPolishNotationInfrastructureRepository polishNotationInfrastructureRepository;

    public Validation<ErrorResponse,BigDecimal> getPolishNotion(final String transactionId,final RequestUser requestUser,final RequestSystem requestSystem,final PolishNotationDomain polishNotationDomain)
    {
        return polishNotationInfrastructureRepository.getPolishNotation(transactionId,requestUser,requestSystem,polishNotationDomain);
    }
}
