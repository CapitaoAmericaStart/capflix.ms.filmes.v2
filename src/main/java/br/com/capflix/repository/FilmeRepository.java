package br.com.capflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capflix.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>{

}
