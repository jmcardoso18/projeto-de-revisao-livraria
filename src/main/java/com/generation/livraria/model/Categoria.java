package com.generation.livraria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity//Indicando que essa classe é uma entidade
@Table(name="tb_categorias")//Nome da tabela que essa classe ira gerar
public class Categoria {
	
	@Id//Indica que esse campo é uma chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Será um atributo auto incremental
	private Long Id;
	
	@Column(length=100)//Podemos definir nessa anotação name,nullable(se aceita valor nulo),unique,insertable,updatable)
	@NotBlank(message = "O Atributo descricao é obrigatório")
	@Size(min = 3, max = 100, message = "A descricao deve ter entre 3 e 100 caracteres")
	private String descricao;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
