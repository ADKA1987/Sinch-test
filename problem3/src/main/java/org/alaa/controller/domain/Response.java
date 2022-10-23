package org.alaa.controller.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Response
{
    @JsonProperty("result")
    String result;
}
