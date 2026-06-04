package br.com.wgsdev.resfood.api.exceptionhandler;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class Problem {
    
    private LocalDateTime dataHora;
    private String mensagem;
    
}