package org.alaa.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Result
{
    BigDecimal value;
}
