package br.com.erudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupoportedMathOperationException extends RuntimeException {

	public UnsupoportedMathOperationException(String msg) {
		super(msg);
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
