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

import com.luciana.desafio.services.exceptions.DatabaseException;
import com.luciana.desafio.services.exceptions.InsufficientStockException;
import com.luciana.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ResourceExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)				
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Recurso nao encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	
	@ExceptionHandler(DatabaseException.class)				
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Erro de Banco de Dado";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)				
	public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
		String error = "Erro de validacao de dados";
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
		String error = "Erro de validacao de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String msg = e.getConstraintViolations().stream()
             .map(ConstraintViolation::getMessage)
             .collect(Collectors.joining(" / "));
		StandardError erro = new StandardError(Instant.now(), status.value(), error, msg , request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(InsufficientStockException.class)				
	public ResponseEntity<StandardError> insufficientStock(InsufficientStockException e, HttpServletRequest request) {
		String error = "Estoque insuficiente";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
}
