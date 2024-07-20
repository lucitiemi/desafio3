package com.luciana.desafio.resources.exceptions;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.luciana.desafio.services.exceptions.CanceledOrderException;
import com.luciana.desafio.services.exceptions.DatabaseException;
import com.luciana.desafio.services.exceptions.EmptyOrderException;
import com.luciana.desafio.services.exceptions.GenerationTokenException;
import com.luciana.desafio.services.exceptions.IncorrectPasswordException;
import com.luciana.desafio.services.exceptions.InsufficientStockException;
import com.luciana.desafio.services.exceptions.InvalidDateException;
import com.luciana.desafio.services.exceptions.InvalidResetPasswordTokenException;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;
import com.luciana.desafio.services.exceptions.UnavailableOrderException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ResourceExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)				
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	
	@ExceptionHandler(DatabaseException.class)				
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Bad Request - Erro de Banco de Dado";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)				
	public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
		String error = "Bad Request - Erro de validacao de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String msg = "";
		for(ObjectError er: e.getBindingResult().getAllErrors()) {
			msg = msg + er.getDefaultMessage() + " / ";
		}
		StandardError erro = new StandardError(Instant.now(), status.value(), error, msg , request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)				
	public ResponseEntity<StandardError> constraintViolation(ConstraintViolationException e, HttpServletRequest request) {
		String error = "Bad Request - Erro de validacao de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String msg = e.getConstraintViolations().stream()
             .map(ConstraintViolation::getMessage)
             .collect(Collectors.joining(" / "));
		StandardError erro = new StandardError(Instant.now(), status.value(), error, msg , request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(InsufficientStockException.class)				
	public ResponseEntity<StandardError> insufficientStock(InsufficientStockException e, HttpServletRequest request) {
		String error = "Bad Request";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(EmptyOrderException.class)				
	public ResponseEntity<StandardError> emptySale(EmptyOrderException e, HttpServletRequest request) {
		String error = "Bad Request";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(UnavailableOrderException.class)				
	public ResponseEntity<StandardError> unavailableOrder(UnavailableOrderException e, HttpServletRequest request) {
		String error = "Bad Request";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(CanceledOrderException.class)				
	public ResponseEntity<StandardError> canceledOrder(CanceledOrderException e, HttpServletRequest request) {
		String error = "Bad Request";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(GenerationTokenException.class)				
	public ResponseEntity<StandardError> errorWhileGeneratingToken(GenerationTokenException e, HttpServletRequest request) {
		String error = "Bad Request";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	
	@ExceptionHandler(InvalidResetPasswordTokenException.class)				
	public ResponseEntity<StandardError> invalidResetPasswordTokenException(InvalidResetPasswordTokenException e, HttpServletRequest request) {
		String error = "Bad Request";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(IncorrectPasswordException.class)				
	public ResponseEntity<StandardError> incorrectPasswordException(IncorrectPasswordException e, HttpServletRequest request) {
		String error = "Bad Request";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(InvalidDateException.class)				
	public ResponseEntity<StandardError> invalidDateException(InvalidDateException e, HttpServletRequest request) {
		String error = "Bad Request";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
}
