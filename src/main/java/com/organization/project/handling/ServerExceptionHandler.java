package com.organization.project.handling;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.organization.project.model.GenericResponse;

@RestControllerAdvice
public class ServerExceptionHandler {
	
	/**
	 * Method exception IllegalArgumentException, NullPointerException, NumberFormatException
	 * @param HttpServletResponse response
	 * @throws IOException
	 */
	
	@ExceptionHandler({IllegalArgumentException.class, NullPointerException.class, NumberFormatException.class})
	public void handleBaseException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	

	/**
	 * Method exception MethodArgumentNotValidException
	 * @param MethodArgumentNotValidException exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<?> handlingValidation(MethodArgumentNotValidException exception, HttpServletRequest request){
		StringBuffer errors = new StringBuffer();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		for (FieldError fieldError : fieldErrors){
			if (errors.length() > 0) errors.append(", ");
			
			errors.append(fieldError.getField()+":"+fieldError.getDefaultMessage());
		}

		return new ResponseEntity<>(new GenericResponse(new Date(), true, errors.toString(), null, request.getRequestURI()), HttpStatus.BAD_REQUEST);
	}
	
}
