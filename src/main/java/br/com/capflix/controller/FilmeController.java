package br.com.capflix.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.capflix.model.dto.FilmeEntradaDto;
import br.com.capflix.model.dto.FilmeSaidaDto;
import br.com.capflix.service.FilmeService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("filme")
@Log4j2
@Validated
public class FilmeController {

	@Autowired
	private FilmeService service;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public FilmeSaidaDto criar(@Valid @RequestBody FilmeEntradaDto entradaDto) {
		log.info("salvar: {}", entradaDto);

		return service.criar(entradaDto);
	}
	
	@PutMapping("id/{id}")
	public void alterar(@PathVariable("id") Long id, @Valid @RequestBody FilmeEntradaDto entradaDto) {
		log.info("alterar: {}, {}", id, entradaDto);

		service.alterar(id, entradaDto);
	}
	
	@GetMapping("id/{id}")
	public FilmeSaidaDto pagarUm(@PathVariable("id") Long id) {
		log.info("pagarUm: {}", id);
		
		return service.pagarUm(id);
	}
	
	@DeleteMapping("id/{id}")
	public void excluir(@PathVariable("id") Long id) {
		log.info("excluir: {}", id);
		
		service.excluir(id);
	}
	
	@GetMapping
	public List<FilmeSaidaDto> listar() {
		log.info("listar");
		
		return service.listar();
	}
}
