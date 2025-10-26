package com.generation.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.livraria.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Busca por descrição (ignorando maiúsculas/minúsculas)
    public List<Produto> findAllByTituloContainingIgnoreCase(String Titulo);
    // SELECT * FROM tb_produto WHERE descricao ILIKE '%?%';

 // Produtos com preço acima de determinado valor (ordem crescente)
    List<Produto> findAllByPrecoGreaterThanOrderByPrecoAsc(Double preco);
    // SELECT * FROM tb_produtos WHERE preco > ? ORDER BY preco ASC;

    // Produtos com preço abaixo de determinado valor (ordem decrescente)
    List<Produto> findAllByPrecoLessThanOrderByPrecoDesc(Double preco);
    // SELECT * FROM tb_produtos WHERE preco < ? ORDER BY preco DESC;

}
