package br.com.wgsdev.resfood.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class EntidadeNaoEncontradaException extends NegocioException {
    
    private static final long serialVersionUID = 1L;
    
    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
    
}
