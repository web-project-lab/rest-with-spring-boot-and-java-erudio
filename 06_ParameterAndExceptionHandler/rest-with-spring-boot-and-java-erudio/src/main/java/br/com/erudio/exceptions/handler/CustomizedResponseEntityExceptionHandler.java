package br.com.erudio.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.erudio.exceptions.ExceptionResponse;
import br.com.erudio.exceptions.UnsupoportedMathOperationException;

/**
 * Qualquer exception que os outros controllers não conseguirem pegar, esta controller aqui vai pegar
 * @author wagne
 *
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), 
				ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@ExceptionHandler(UnsupoportedMathOperationException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRquestException(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), 
				ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);		
	}

}
