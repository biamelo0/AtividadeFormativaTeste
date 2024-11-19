package com.teste.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.teste.entities.Produto;

class ProdutoTest {
	
	private Produto produto;

	
	@BeforeEach
	void setUp() {
		//Arrange
		produto = new Produto (1L, "Cadeira", "cadeira de metal", 34.56);
	}

	@Test
	@DisplayName("Testando o getter e setter do campo ID")
	void testId() {
		//Act
		produto.setId(2L);
		//Assert
		assertEquals(2L,produto.getId());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo nome")
	void testNome() {
		//Act
		produto.setNome("Cadeira");
		//Assert
		assertEquals("Cadeira",produto.getNome());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo descricao")
	void testTelefone() {
		//Act
		produto.setDescricao("cadeira de metal");
		//Assert
		assertEquals("159989-3423",produto.getDescricao());
	}
	
	
	@Test
	@DisplayName("Testando o getter e setter do campo preco")
	void testPreco() {
		//Act
		produto.setPreco(98.88);
		//Assert
	assertEquals("Casal",produto.getPreco());
	}
	
	
	@Test
	@DisplayName ("Testando o construtor com todos os argumentos")
	void testeConstrutorAll() {
		Produto novoProduto;
		//Act
		produto = novoProduto = new Produto (3L,"Cadeira", "Cadeira de metal", 34.99);
		//assertAll
		assertAll("novoProduto",
				()-> assertEquals(3L,novoProduto.getId()),
				()-> assertEquals("Cadeira",novoProduto.getNome()),
				()-> assertEquals("Cadeira de metal",novoProduto.getDescricao()),
				()-> assertEquals(23.55,novoProduto.getPreco()));
	}


}
