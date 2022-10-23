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
public class RequestUser
{
    String value;

    private final static String REQUESTING_USER_CANNOT_BE_EMPTY = "RequestingSystem cannot be empty";

    public static Validation<String, RequestUser> validate(final String value) {

        return null == value || Objects.requireNonNull(value).isBlank() ?
                Validation.invalid( REQUESTING_USER_CANNOT_BE_EMPTY)
                : Validation.valid(new RequestUser(value));
    }
}
