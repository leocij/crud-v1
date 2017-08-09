package com.crud.v1.exception;

public class AlunoAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlunoAlreadyExistException(String mensagem) {
		super(mensagem);
	}
	
	public AlunoAlreadyExistException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
