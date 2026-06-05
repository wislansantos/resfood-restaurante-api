package br.com.wgsdev.resfood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import br.com.wgsdev.resfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.wgsdev.resfood.domain.exception.NegocioException;
import br.com.wgsdev.resfood.domain.exception.EntidadeEmUsoException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
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
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex) {
		
		Problem problema = Problem.builder()
		    .dataHora(LocalDateTime.now())
		    .mensagem(ex.getMessage())
		    .build();
		
		return ResponseEntity
		    .status(HttpStatus.CONFLICT)
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
    
}
