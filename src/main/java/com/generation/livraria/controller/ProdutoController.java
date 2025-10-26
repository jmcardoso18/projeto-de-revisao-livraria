package com.generation.livraria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.livraria.model.Produto;
import com.generation.livraria.repository.CategoriaRepository;
import com.generation.livraria.repository.ProdutoRepository;

import jakarta.validation.Valid;

	@RestController
	@RequestMapping("/produtos")
	@CrossOrigin(origins ="*",allowedHeaders = "*")
	public class ProdutoController{

		@Autowired
		private ProdutoRepository produtoRepository;
		
		@Autowired
		private CategoriaRepository categoriaRepository;
		
		@GetMapping
		public ResponseEntity<List<Produto>> getall(){
			return ResponseEntity.ok(produtoRepository.findAll());
			//Select*from td_postagens
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Produto> getByid(@PathVariable Long id){
			return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		
		@GetMapping("/titulo/{titulo}")
		public ResponseEntity<List<Produto>> getAllByTitulo(@PathVariable String titulo){
			return ResponseEntity.ok(produtoRepository.findAllByTituloContainingIgnoreCase(titulo));
			//Select*from td_produtos
		}
		
		// Produtos com preço acima de X (ordem crescente)
		@GetMapping("/preco/acima/{valor}")
		public ResponseEntity<List<Produto>> getPrecoAcima(@PathVariable Double valor) {
		    return ResponseEntity.ok(produtoRepository.findAllByPrecoGreaterThanOrderByPrecoAsc(valor));
		}

		// Produtos com preço abaixo de X (ordem decrescente)
		@GetMapping("/preco/abaixo/{valor}")
		public ResponseEntity<List<Produto>> getPrecoAbaixo(@PathVariable Double valor) {
		    return ResponseEntity.ok(produtoRepository.findAllByPrecoLessThanOrderByPrecoDesc(valor));
		}
		
		// Criar novo produto
		@PostMapping
		public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {

		    // Verifica se a categoria informada existe
		    var categoria = categoriaRepository.findById(produto.getCategoria().getId())
		            .orElseThrow(() -> new ResponseStatusException(
		                    HttpStatus.BAD_REQUEST, "A categoria informada não existe!"
		            ));

		    produto.setId(null); // Garante criação de novo registro
		    return ResponseEntity.status(HttpStatus.CREATED)
		            .body(produtoRepository.save(produto));
		}


		// Atualizar produto existente
		@PutMapping
		public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {

		    // Verifica se o produto existe
		    if (!produtoRepository.existsById(produto.getId())) {
		        return ResponseEntity.notFound().build();
		    }

		    // Verifica se a categoria informada existe
		    var categoria = categoriaRepository.findById(produto.getCategoria().getId())
		            .orElseThrow(() -> new ResponseStatusException(
		                    HttpStatus.BAD_REQUEST, "A categoria informada não existe!"
		            ));

		    return ResponseEntity.status(HttpStatus.OK)
		            .body(produtoRepository.save(produto));
		}

		
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Long id) {
			Optional<Produto> Produto = produtoRepository.findById(id);
			if(Produto.isEmpty())
					throw new ResponseStatusException(HttpStatus.NOT_FOUND);
				
			produtoRepository.deleteById(id);
			
		}

}
