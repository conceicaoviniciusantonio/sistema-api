package com.example.sistema.model.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.sistema.exceptions.BusinessException;
import com.example.sistema.model.dtos.PessoaDto;
import com.example.sistema.model.entities.Pessoa;
import com.example.sistema.model.geral.ConsultaResponse;
import com.example.sistema.model.repositories.PessoaRepository;
import com.example.sistema.model.requests.PessoaConsultaRequest;
import com.example.sistema.utils.ValidatorUtils;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	private ConversionService conversionService;
	
	@Autowired
	private ValidatorUtils utils;
	
	private Pessoa insert(Pessoa p) throws BusinessException {
		this.utils.validate(p);
		
		return this.pessoaRepository.save(p);
	}
	
	public PessoaDto insert(PessoaDto pessoa) throws BusinessException{
		return this.conversionService.convert(this.insert(this.conversionService.convert(pessoa, Pessoa.class)),
				PessoaDto.class);
	}
	
	
	public Optional<PessoaDto> get(Integer id){
		final Optional<Pessoa> op = pessoaRepository.findById(id);
		
		if (op.isPresent()) {
			return Optional.of(this.conversionService.convert(op.get(), PessoaDto.class));
		}

		return Optional.empty();
	}
	
	public  void excluir(Integer id) throws BusinessException {
		this.pessoaRepository.deleteById(id);
	}
	
	private Pessoa alterar(Pessoa p) throws BusinessException {
		this.utils.validate(p);
		
		return this.pessoaRepository.save(p);
	}
	
	public PessoaDto alterar(PessoaDto p) throws BusinessException {
		return this.conversionService.convert(this.alterar(this.conversionService.convert(p, Pessoa.class)), PessoaDto.class);
	}
	
	public ConsultaResponse<PessoaDto> pesquisar(PessoaConsultaRequest request) {
		final String filtroString = ("%" + (request.getFiltro() == null ? "" : request.getFiltro().trim()) + "%")
				.replace(" ", "%").toUpperCase();

		final Direction direction = StringUtils.isEmpty(request.getDirecao()) ? Direction.ASC
				: Direction.valueOf(request.getDirecao().toUpperCase());

		final String ordenar = StringUtils.isEmpty(request.getOrdenar()) ? "nome" : request.getOrdenar();

		final Page<Pessoa> page = this.pessoaRepository.findByNome(filtroString,
				PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(direction, ordenar)));

		return new ConsultaResponse<>(page.getContent().stream()
				.map(a -> this.conversionService.convert(a, PessoaDto.class)).collect(Collectors.toList()),
				page.getTotalElements(), page.getTotalPages());
	}
	
}
