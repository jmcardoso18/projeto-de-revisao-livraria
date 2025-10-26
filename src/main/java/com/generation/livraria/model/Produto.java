package com.generation.livraria.model;

import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

	
	@Entity
	@Table(name="tb_produtos")//create table tb_postagens
	public class Produto {
		
		@Id //Primary key (id)
		@GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
		private Long id;
		

		@NotBlank(message= "O atrituto titulo é obrigatório!")
		private String titulo;
		
		@Column(length=255)
		@NotBlank(message= "O atrituto autor é obrigatório!")
		private String autor;
		
		@NotNull(message= "O atrituto preço é obrigatório!")
		@PositiveOrZero(message="O preço deve ser positivo ou zero")
		private Double preco;
			
		@NotBlank(message= "O atrituto foto é obrigatório!")
		private String Foto;
		
		@ManyToOne
		@JsonIgnoreProperties("produto")
		private Categoria categoria;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String gettitulo() {
			return titulo;
		}

		public void settitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getautor() {
			return autor;
		}

		public void setautor(String autor) {
			this.autor = autor;
		}

		public Double getPreco() {
			return preco;
		}

		public void setPreco(Double preco) {
			this.preco = preco;
		}

		
		public String getFoto() {
			return Foto;
		}

		public void setFoto(String foto) {
			Foto = foto;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
			
	}

