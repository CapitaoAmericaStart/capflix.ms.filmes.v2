package br.com.capflix.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class NegocioException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	private final HttpStatus codigoHttp;
	private final String erro;
}