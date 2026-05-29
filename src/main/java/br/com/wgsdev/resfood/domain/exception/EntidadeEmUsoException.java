package br.com.wgsdev.resfood.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }
    
}
