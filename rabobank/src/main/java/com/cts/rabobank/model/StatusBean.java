package com.cts.rabobank.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * StatusBean
 */
@Setter
@Getter
public class StatusBean {
    private String message;
    private int code;
}
