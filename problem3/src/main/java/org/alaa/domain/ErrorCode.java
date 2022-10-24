package org.alaa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode
{
    VALIDATION_ERROR_CODE(4000),
    BUSINESS_VALIDATION_ERROR_CODE(4001),

    INTERNAL_SERVER_ERROR(50001);
    final int value;


}
