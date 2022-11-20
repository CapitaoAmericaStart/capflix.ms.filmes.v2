package br.com.capflix.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilmeEntradaDto {
	
	@NotBlank(message="obrigatório")
	@Size(max=255, message="máximo 255 caracteres") // nao esta na historia
	private String nome;
	
	@Size(max=500, message="máximo 500 caracteres")
	private String descricao;
	
	@NotBlank(message="obrigatório")
	@URL(message="inválido")
	@Size(max=255, message="máximo 255 caracteres") // nao esta na historia
	private String url;
	
	@NotBlank(message="obrigatório")
	@Size(min=4, max=20, message="mínimo 4 e máximo 20 caracteres")
	private String genero;
}
