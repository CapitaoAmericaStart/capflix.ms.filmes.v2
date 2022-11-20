package br.com.capflix.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.capflix.exception.NegocioException;
import br.com.capflix.model.Filme;
import br.com.capflix.model.dto.FilmeEntradaDto;
import br.com.capflix.model.dto.FilmeSaidaDto;
import br.com.capflix.repository.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public FilmeSaidaDto criar(FilmeEntradaDto entradaDto) {
		Filme filme = mapper.map(entradaDto, Filme.class);
		
		Filme filmeBanco = repository.save(filme);
		
		return mapper.map(filmeBanco, FilmeSaidaDto.class);
	}

	public void alterar(Long id, FilmeEntradaDto entradaDto) {
		Optional<Filme> optional = repository.findById(id);
		
		if(!optional.isPresent()) {
			throw new NegocioException(HttpStatus.NOT_FOUND, "filme não encontrado");
		}
		
		Filme filmeBanco = optional.get();
		
		mapper.map(entradaDto, filmeBanco);

		repository.save(filmeBanco);
	}

	public FilmeSaidaDto pagarUm(Long id) {
		Optional<Filme> optional = repository.findById(id);
		
		if(!optional.isPresent()) {
			throw new NegocioException(HttpStatus.NOT_FOUND, "filme não encontrado");
		}
		
		Filme filmeBanco = optional.get();
		
		return mapper.map(filmeBanco, FilmeSaidaDto.class);	
	}

	public void excluir(Long id) {
		if(!repository.existsById(id)) {
			throw new NegocioException(HttpStatus.NOT_FOUND, "filme não encontrado");
		}
		
		repository.deleteById(id);		
	}

	public List<FilmeSaidaDto> listar() {
		List<Filme> lista = repository.findAll();
		
		return mapper.map(lista, new TypeToken<List<FilmeSaidaDto>>() {}.getType());
	}
}
