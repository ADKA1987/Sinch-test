package org.alaa.domain;

import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.alaa.controller.domain.Request;

import java.util.Objects;

import static org.alaa.domain.ErrorCode.VALIDATION_ERROR_CODE;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class PolishNotationDomain
{
    String value;

    private final static String PolishNotationDomain_CANNOT_BE_EMPTY = "PolishNotationDomain cannot be empty";

    public static Validation<Error, PolishNotationDomain> validate(final Request value) {

        return null == value.getInput() || Objects.requireNonNull(value.getInput()).isBlank() ?
                Validation.invalid(new Error(VALIDATION_ERROR_CODE.value, PolishNotationDomain_CANNOT_BE_EMPTY))
                : Validation.valid(new PolishNotationDomain(value.getInput()));
    }
}
