package org.alaa.infrastructure.domain;

import io.vavr.control.Either;
import org.alaa.domain.ErrorResponse;
import org.alaa.domain.PolishNotationDomain;
import org.alaa.domain.RequestSystem;
import org.alaa.domain.RequestUser;
import org.alaa.domain.Result;
import reactor.core.publisher.Mono;

 public interface IPolishNotationInfrastructureRepository
{
    Mono<Either<ErrorResponse, Result>> getPolishNotation(final String transactionId, final RequestUser requestUser,final RequestSystem requestSystem,final PolishNotationDomain polishNotationDomain);
}
