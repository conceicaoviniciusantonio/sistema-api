package com.example.sistema.model.resources;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistema.exceptions.BusinessException;
import com.example.sistema.model.dtos.PessoaDto;
import com.example.sistema.model.geral.ConsultaResponse;
import com.example.sistema.model.requests.PessoaConsultaRequest;
import com.example.sistema.model.services.PessoaService;

@RestController
@RequestMapping("/pessoa/v1")
public class PessoaResource {

	@Autowired
	PessoaService pessoaService;
	
	@PostMapping
	public PessoaDto inserir(@RequestBody @Valid PessoaDto pessoa) throws BusinessException {
		return this.pessoaService.insert(pessoa);
	}
	
	@GetMapping("{id}")
	public Optional<PessoaDto> get(@PathVariable Integer id) {
		return this.pessoaService.get(id);
	}
	
	@PutMapping
	public PessoaDto alterar(@RequestBody PessoaDto p) throws BusinessException {
		return this.pessoaService.alterar(p);
	}
	
	@DeleteMapping("{id}")
	public void excluir(@PathVariable Integer id) throws BusinessException {
		this.pessoaService.excluir(id);
	}
	
	@PostMapping("pesquisar")
	public ConsultaResponse<PessoaDto> pesquisar(@RequestBody PessoaConsultaRequest request) {
		return this.pessoaService.pesquisar(request);
	}
}
