package com.crud.v1.restcontroller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crud.v1.entity.Aluno;
import com.crud.v1.representation.AlunoRepresentation;
import com.crud.v1.service.AlunoService;

@CrossOrigin
@RestController
@RequestMapping("/alunos")
public class AlunoRestController {
	@Autowired
	private AlunoService alunoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<List<AlunoRepresentation>> listAll() {
		List<Aluno> alunos = alunoService.findAll();
		List<AlunoRepresentation> alunoRepresentation = new ArrayList<>();
		for (Aluno aluno : alunos) {
			alunoRepresentation.add(new AlunoRepresentation(aluno));
		}
		return ResponseEntity.ok(alunoRepresentation);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HttpEntity<AlunoRepresentation> findById(@PathVariable("id") Long id) {

		Aluno aluno = alunoService.findById(id);
		AlunoRepresentation alunoRepresentation = new AlunoRepresentation(aluno);

		return ResponseEntity.ok(alunoRepresentation);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> save(@Valid @RequestBody AlunoRepresentation alunoRepresentation) {

		Aluno aluno = alunoService.save(AlunoRepresentation.build(alunoRepresentation));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> update(@RequestBody AlunoRepresentation alunoRepresentation,
			@PathVariable("id") Long id) {

		alunoRepresentation.setIdentifier(id);
		alunoService.update(AlunoRepresentation.build(alunoRepresentation));

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<AlunoRepresentation> delete(@PathVariable("id") Long id) {

		Aluno aluno = alunoService.findById(id);
		AlunoRepresentation alunoRepresentation = new AlunoRepresentation(aluno);
		alunoService.delete(aluno);
		return ResponseEntity.ok(alunoRepresentation);
	}
}
