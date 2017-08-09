package com.crud.v1.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crud.v1.exception.AlunoAlreadyExistException;
import com.crud.v1.exception.AlunoNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	//Aluno
	@ExceptionHandler(AlunoNotFoundException.class)
	public ResponseEntity<ErrorDetails> handlerAlunoNotFoundException(AlunoNotFoundException e, HttpServletRequest request) {
		e.printStackTrace();
		ErrorDetails error = new ErrorDetails();
		error.setStatus(4041);
		error.setTitle("Aluno not found.");
		error.setMessage(e.getMessage());
		error.setUrl("http://erros.teste.com/404");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(AlunoAlreadyExistException.class)
	public ResponseEntity<ErrorDetails> handlerAlunoAlreadyExistException(AlunoAlreadyExistException e, HttpServletRequest request) {
		e.printStackTrace();
		ErrorDetails error = new ErrorDetails();
		error.setStatus(4091);
		error.setTitle("Aluno already exist.");
		error.setUrl("http://erros.teste.com/409");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
}
