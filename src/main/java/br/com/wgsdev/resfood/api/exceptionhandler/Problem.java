package br.com.wgsdev.resfood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Builder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class Problem {
    
    private Integer status;
    private OffsetDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Object> objects;
    
    @Getter
    @Builder
    public static class Object {
        
        private String name;
        private String userMessage;
        
    }
    
}
