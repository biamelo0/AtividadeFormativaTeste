package com.teste.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.teste.entities.Produto;

@DataJpaTest
class ProdutoRepositsoryTest {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@DisplayName("Testando o Save")
	@Test
	void testSalvarRepository() {
		Produto produto1 =new Produto (null, "Cadeira", "Cadeira de metal",89.00);
		
		Produto saveProduto = produtoRepository.save(produto1);
		
		assertNotNull(saveProduto);
		assertTrue(saveProduto.getId() > 0);					
	}
	
	@DisplayName("Testando Get para todos produtos")
	@Test
	void testGetAllRepository() {
		Produto produto1 =new Produto (null, "Cadeira", "Cadeira de metal", 89.00);
		Produto produto2 =new Produto (null, "Cadeira", "Cadeira de metal", 89.00);
		
		produtoRepository.save(produto1);
		produtoRepository.save(produto2);
		
		List<Produto> produtoList =  produtoRepository.findAll();
		
		assertNotNull(produtoList);
		assertEquals(2,produtoList.size());
		
	}
	
	@DisplayName("Testando GET by ID")
	@Test
	void testGetById() {
		Produto produto1 =new Produto (null, "Cadeira", "Cadeira de metal",89.00);
	
		
		produtoRepository.save(produto1);
		
		Produto saveProduto = produtoRepository.findById(produto1.getId()).get();
		

		assertNotNull(saveProduto);
		assertEquals(produto1.getId(), saveProduto.getId());
		
	}
	
	@DisplayName("Testando update")
	@Test
	void testUpdateProduto() {
		Produto produto1 =new Produto (null, "Cadeira", "Cadeira de metal",89.00);
	
		
		produtoRepository.save(produto1);
		
		Produto saveProduto = produtoRepository.findById(produto1.getId()).get();
		produto1.setNome("Cadeira");
		produto1.setDescricao("Cadeira de metal");
		produto1.setPreco(89.00);
		
		Produto updateProduto = produtoRepository.save(saveProduto);
		

		assertNotNull(updateProduto);
		assertEquals("Cadeira", updateProduto.getNome());
		assertEquals("Cadeira de metal", updateProduto.getDescricao());
		assertEquals(89.00, updateProduto.getPreco());
		
	}
	
	@DisplayName("Testando delete")
	@Test
	void testDeleteProduto() {
		Produto produto1 =new Produto (null, "Cadeira", "Cadeira de metal",89.00);
	
		
		produtoRepository.save(produto1);
		
		produtoRepository.deleteById(produto1.getId());
		
		Optional<Produto> produtoOptional = produtoRepository.findById(produto1.getId());

		assertTrue(produtoOptional.isEmpty());
	}
	
}

