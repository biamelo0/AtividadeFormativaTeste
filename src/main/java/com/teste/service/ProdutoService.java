package com.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.entities.Produto;
import com.teste.repository.ProdutoRepository;


@Service
public class ProdutoService {
	
	

	@Autowired
	private final ProdutoRepository produtoRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public List<Produto> listarTodos(){
		return produtoRepository.findAll();
	}
	public Optional<Produto> buscaProdutoId(Long id) {
		return produtoRepository.findById(id);
	}
	public Produto salvaProduto(Produto produto) {
		return produtoRepository.save(produto);		
	}
	public Produto alterarProduto(Produto produto) {
		if (produtoRepository.existsById(produto.getId())) {
			return produtoRepository.save(produto);
		}else {
			throw new RuntimeException("Produto não encontrado");
		}
	}
	
	public void deletarProduto(Long id) {
		produtoRepository.deleteById(id);
	}

	public boolean apagarProduto(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
