package com.example.sistema.model.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.sistema.model.dtos.PessoaDto;
import com.example.sistema.model.entities.Pessoa;

@Component
public class PessoaEntityToDtoConverter implements Converter<Pessoa, PessoaDto>{
	
	@Override
	public PessoaDto convert(Pessoa source) {
		final PessoaDto destino = new PessoaDto();
		
		destino.setId(source.getId());
		destino.setNome(source.getNome());
		destino.setDataNascimento(source.getDataNascimento());
		destino.setRua(source.getRua());
		destino.setEstado(source.getEstado());
		destino.setCidade(source.getCidade());
		destino.setNumero(source.getNumero());
		destino.setCep(source.getCep());
		destino.setTelefoneFixo(source.getTelefoneFixo());
		destino.setTelefoneCelular(source.getTelefoneCelular());
		
		return destino;
	}

}
