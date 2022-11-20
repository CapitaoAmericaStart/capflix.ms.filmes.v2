package br.com.capflix.conf;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import  org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.capflix.exception.NegocioException;

@RestControllerAdvice
public class ErroController {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(NegocioException.class)
	@ResponseBody
	public ResponseEntity<String> handle(NegocioException e) {
		return ResponseEntity.status(e.getCodigoHttp()).body(e.getErro());
	}
	
	//validacao: @RequestBody @Valid
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ResponseEntity<List<String>> handle(BindException e) {
		List<String> validacoes = new ArrayList<>();

		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			validacoes.add(error.getField()+": "+mensagem);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validacoes);
	}
	
	//validacao: parametro
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ResponseEntity<List<String>> handle(ConstraintViolationException e) {
		List<String> validacoes = new ArrayList<>();

		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
			validacoes.add(path + ": " + violation.getMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validacoes);
	}
}
