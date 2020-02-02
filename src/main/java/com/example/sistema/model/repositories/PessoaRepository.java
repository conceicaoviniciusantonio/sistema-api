package com.example.sistema.model.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sistema.model.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	@Query("SELECT e FROM Pessoa e WHERE UPPER(e.nome) LIKE UPPER(:filtro)")
	Page<Pessoa> findByNome(@Param(value = "filtro") String filtro, Pageable pageable);
}
