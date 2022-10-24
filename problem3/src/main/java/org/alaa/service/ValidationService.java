package org.alaa.service;

import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.alaa.controller.domain.Request;
import org.alaa.domain.Error;
import org.alaa.domain.PolishNotationDomain;
import org.alaa.domain.RequestSystem;
import org.alaa.domain.RequestUser;

public class ValidationService
{
    public Validation<Seq<Error>, Tuple3<RequestUser, RequestSystem, PolishNotationDomain>> validateRequest( final String requestUser,
                                                                                                        final String requestSystem,
                                                                                                        final Request request)
    {
        return Validation.combine(RequestUser.validate(requestUser),
                RequestSystem.validate(requestSystem),
                PolishNotationDomain.validate(request))
                .ap(Tuple::of);
    }
}
