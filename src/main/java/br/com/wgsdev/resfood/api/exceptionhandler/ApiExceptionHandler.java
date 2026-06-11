package br.com.wgsdev.resfood.api.exceptionhandler;

import java.util.stream.Collectors;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.wgsdev.resfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.wgsdev.resfood.domain.exception.NegocioException;
import br.com.wgsdev.resfood.domain.exception.EntidadeEmUsoException;
import br.com.wgsdev.resfood.api.exceptionhandler.ProblemType;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe";
		
		Problem problema = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problema = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = ex.getMessage();
		
		Problem problema = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
		Problem problema = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		
		Problem problema = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		if (body == null) {
			body = Problem.builder()
			    .status(status.value())
			    .title(HttpStatus.valueOf(status.value()).getReasonPhrase())
			    .build();
		} else if (body instanceof String) {
			body = Problem.builder()
			    .status(status.value())
			    .title((String) body)
		        .build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status, ProblemType problemType, String detail) {
		return Problem.builder()
		    .status(status.value())
		    .type(problemType.getUri())
		    .title(problemType.getTitle())
		    .detail(detail);
	}
    
}
