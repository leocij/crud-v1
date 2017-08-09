package com.crud.v1.representation;

import org.springframework.hateoas.ResourceSupport;

import com.crud.v1.entity.Aluno;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AlunoRepresentation extends ResourceSupport {

	@JsonInclude(Include.NON_NULL)
	private Long identifier;
	@JsonInclude(Include.NON_NULL)
	private String nome;
	@JsonInclude(Include.NON_NULL)
	private Integer idade;

	public AlunoRepresentation() {
		super();
	}

	public AlunoRepresentation(Aluno aluno) {
		super();
		this.identifier = aluno.getId();
		this.nome = aluno.getNome();
		this.idade = aluno.getIdade();
	}
	
	public static Aluno build(AlunoRepresentation alunoRepresentation) {
		Aluno aluno = new Aluno();
		aluno.setId(alunoRepresentation.getIdentifier());
		aluno.setNome(alunoRepresentation.getNome());
		aluno.setIdade(alunoRepresentation.getIdade());
		return aluno;
	}

	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

}
