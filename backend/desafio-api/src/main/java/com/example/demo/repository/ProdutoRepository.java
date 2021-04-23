package com.example.demo.repository;

import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    List<Produto> findByCategoria(String categoria);

    List<Produto> findByNomeContaining(String nome);
}