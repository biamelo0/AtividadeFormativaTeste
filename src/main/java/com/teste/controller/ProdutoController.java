package com.teste.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.teste.entities.Produto;
import com.teste.service.ProdutoService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private final ProdutoService produtoService;

	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produto>> buscaProdutoControlId(@PathVariable Long id){
		Optional<Produto> produto  = produtoService.buscaProdutoId(id);
		if(produto != null) {
			return ResponseEntity.ok(produto);
		}
		else {
			return ResponseEntity.notFound().build();
		}

	}
	@GetMapping
	public ResponseEntity<List<Produto>> buscaTodosProdutosControl(){
		List<Produto> produtos = produtoService.listarTodos();
		return ResponseEntity.ok(produtos);
	}

	@PostMapping
	public ResponseEntity<Produto> salvaProdutosControl(@RequestBody @Valid Produto produto){
		Produto salvaProduto = produtoService.salvaProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaProduto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> alteraProdutoControl(@PathVariable Long id, @RequestBody @Valid Produto produto){
		Produto alteraProduto = produtoService.alterarProduto(produto);
		if(alteraProduto != null) {
			return ResponseEntity.ok(produto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagaProdutoControl(@PathVariable Long id){
		boolean apagar = produtoService.apagarProduto(id);
		if (apagar) {
			return ResponseEntity.ok().body("O Produto foi excluido com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
