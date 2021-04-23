package com.example.demo.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Produto;
import com.example.demo.repository.ProdutoRepository;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class Controller {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/produtos")
	public ResponseEntity<List<Produto>> getAllProdutos(@RequestParam(required = false) String nome) {
		try {
			List<Produto> produtos = new ArrayList<Produto>();

			if (nome == null)
				produtoRepository.findAll().forEach(produtos::add);
			else
				produtoRepository.findByNomeContaining(nome).forEach(produtos::add);

			if (produtos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(produtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/produtos/{id}")
	public ResponseEntity<Produto> findProdutoById(@PathVariable("id") long id) {
		try {
			Optional<Produto> produtoData = produtoRepository.findById(id);

			if (produtoData.isPresent()) {
				return new ResponseEntity<>(produtoData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/produtos")
	public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
		try {
			Produto _produto = produtoRepository
					.save(new Produto(produto.getValor(), produto.getCodigo(), produto.getNome(), produto.getCategoria(), produto.getStatus()));
			return new ResponseEntity<>(_produto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/produtos/{id}")
	public ResponseEntity<Produto> updateProduto(@PathVariable("id") Long id, @RequestBody Produto produto) {
		Optional<Produto> produtoData = produtoRepository.findById(id);
			
		if (produtoData.isPresent()) {
			Produto _produto = produtoData.get();
			_produto.setNome(produto.getNome());
			_produto.setCodigo(produto.getCodigo());
			_produto.setValor(produto.getValor());
			_produto.setCategoria(produto.getCategoria());
			_produto.setStatus(produto.getStatus());
			return new ResponseEntity<>(produtoRepository.save(_produto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/produtos/{id}")
	public ResponseEntity<HttpStatus> deleteProduto(@PathVariable("id") long id) {
		try {
			produtoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/produtos")
	public ResponseEntity<HttpStatus> deleteAllProdutos() {
		try {
			produtoRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}