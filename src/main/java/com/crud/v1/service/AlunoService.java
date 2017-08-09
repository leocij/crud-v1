package com.crud.v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.v1.entity.Aluno;
import com.crud.v1.exception.AlunoAlreadyExistException;
import com.crud.v1.exception.AlunoNotFoundException;
import com.crud.v1.repository.AlunoRepository;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Transactional(readOnly = true)
	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Aluno findById(Long id) {
		if(!exist(id)) {
			throw new AlunoNotFoundException("Aluno com esse id não existe: " + id);
		}
		return alunoRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	private boolean exist(Long id) {
		return alunoRepository.exists(id);
	}
	
	@Transactional(readOnly = false)
	public Aluno save(Aluno aluno) {
		if(aluno.getId() != null && exist(aluno.getId())) {
			throw new AlunoAlreadyExistException("Aluno com esse id já existe: " + aluno.getId());
		}
		return alunoRepository.save(aluno);
	}
	
	@Transactional(readOnly = false)
	public Aluno update(Aluno aluno) {
		if(!exist(aluno.getId())) {
			throw new AlunoNotFoundException("Aluno com esse id não existe: " + aluno.getId());
		}
		return alunoRepository.save(aluno);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		alunoRepository.delete(id);
	}
	
	@Transactional(readOnly = false)
	public void delete(Aluno aluno) {
		alunoRepository.delete(aluno);
	}
}
