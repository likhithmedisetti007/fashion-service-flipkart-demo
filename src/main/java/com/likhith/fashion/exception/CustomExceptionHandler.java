package com.likhith.fashion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.likhith.fashion.dto.FashionResponse;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(TechnicalException.class)
	public ResponseEntity<FashionResponse> technicalException(TechnicalException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getStatusCode(), exception.getErrorMessage());
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new FashionResponse(errorMessage));
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<FashionResponse> validationException(ValidationException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getStatusCode(), exception.getErrorMessage());
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new FashionResponse(errorMessage));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<FashionResponse> runtimeException(RuntimeException exception) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Unexpected Error occured. Please try again after sometime");
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new FashionResponse(errorMessage));
	}
}