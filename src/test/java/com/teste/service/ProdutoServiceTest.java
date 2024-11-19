package com.teste.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teste.entities.Produto;
import com.teste.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class ProdutoServiceTest {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@BeforeEach
	void setUp() {
		produtoRepository.deleteAll();
	}
	
	@DisplayName("Testando Salvar produto")
	@Test
	void testSalvarProduto() {
		Produto produto = new Produto(null, "Cadeira", "Cadeira de metal",89.00);
		
		Produto resultado = produtoService.salvaProduto(produto);
		
		assertNotNull(resultado);
		assertEquals("Cadeira", resultado.getNome());
		assertTrue(resultado.getId()>0);
	}
		
		
		@DisplayName("Testando listar todos produtos")
		@Test
		void testListarTodos() {
			Produto produto1 = new Produto(null, "Cadeira", "Cadeira de metal",89.00);
			Produto produto2 = new Produto(null, "Cadeira", "Cadeira de metal",89.00);
			
			produtoService.salvaProduto(produto1);
			produtoService.salvaProduto(produto2);
			
			List<Produto> resultado = produtoService.listarTodos();
			
			assertNotNull(resultado);
			assertEquals(2, resultado.size());
	
		}
		
		@DisplayName("Testando Buscar Produtos por ID")
		@Test
		
		void testBuscarPorId(){
			
		Produto produto = new Produto(null,  "Julia Maria", "Cadeira de metal",89.00);
		
		Produto salvo = produtoService.salvaProduto(produto);
		Optional<Produto> resultado = produtoService.buscaProdutoId(salvo.getId());
		
		assertTrue(resultado.isPresent());
		assertEquals("Julia Maria", resultado.get().getNome());
	}
		
		@DisplayName("Testando atualizar produto")
		@Test
		void testAtualizarProduto() {
		Produto produto = new Produto(null, "Julia", "julia@gmail.com", 89.00);
		Produto salvo = produtoService.salvaProduto(produto);
		
		salvo.setNome("Leonardo");
		salvo.setDescricao("Cadeira de metal");
		salvo.setPreco(89.00);
		
		Produto atualizado = produtoService.alterarProduto(salvo);
		
		assertNotNull(atualizado);
		assertEquals("Cadeira", atualizado.getNome());
		assertEquals("Cadeira de metal", atualizado.getDescricao());
		assertEquals(89.00, atualizado.getPreco());
		}
		
		@DisplayName("Testando deletar produto")
		@Test
		void testDeletarProduto(){
			Produto produto = new Produto (null, "Julia", "julia@gmail.com", 89.00);
			
			Produto salvo = produtoService.salvaProduto(produto);
			produtoService.deletarProduto(salvo.getId());
			
			Optional<Produto> resultado = produtoService.buscaProdutoId(salvo.getId());
			
			assertTrue(resultado.isEmpty());
		}
}

