package br.com.wgsdev.resfood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import br.com.wgsdev.resfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.wgsdev.resfood.domain.exception.NegocioException;

@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex) {
		
		Problem problema = Problem.builder()
		    .dataHora(LocalDateTime.now())
		    .mensagem(ex.getMessage())
		    .build();
		
		return ResponseEntity
		    .status(HttpStatus.NOT_FOUND)
		    .body(problema);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex) {
		
		Problem problema = Problem.builder()
		    .dataHora(LocalDateTime.now())
		    .mensagem(ex.getMessage())
		    .build();
		
		return ResponseEntity
		    .status(HttpStatus.BAD_REQUEST)
		    .body(problema);
	}
    
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> handleHttpMediaTypeNotSupportedException() {
		
	    Problem problema = Problem.builder()
	        .dataHora(LocalDateTime.now())
	        .mensagem("O tipo de mídia não é aceito.")
	        .build();
		
		return ResponseEntity
	        .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	        .body(problema);
	}
    
}
