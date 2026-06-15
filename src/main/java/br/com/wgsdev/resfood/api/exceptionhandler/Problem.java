package br.com.wgsdev.resfood.api.exceptionhandler;

import lombok.Getter;
import lombok.Builder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class Problem {
    
    private Integer status;
    private String type;
    private String title;
    private String detail;
    
    private String userMessage;
    
}
