package org.alaa.infrastructure.domain;


import io.vavr.control.Validation;
import org.alaa.domain.PolishNotationDomain;
import org.alaa.domain.RequestSystem;
import org.alaa.domain.RequestUser;

import java.math.BigDecimal;

 public interface IPolishNotationInfrastructureRepository
{
    Validation<String,BigDecimal> getPolishNotation(final String transactionId, final RequestUser requestUser,final RequestSystem requestSystem,final PolishNotationDomain polishNotationDomain);
}
