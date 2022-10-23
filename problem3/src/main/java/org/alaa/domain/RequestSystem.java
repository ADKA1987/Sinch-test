package org.alaa.domain;

import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RequestSystem
{
    String value;

    private final static String REQUESTING_SYSTEM_CANNOT_BE_EMPTY = "RequestingSystem cannot be empty";

    public static Validation<String, RequestSystem> validate(final String requestingSystem) {

        return null == requestingSystem || Objects.requireNonNull(requestingSystem).isBlank() ?
                Validation.invalid( REQUESTING_SYSTEM_CANNOT_BE_EMPTY)
                : Validation.valid(new RequestSystem(requestingSystem));
    }
}
