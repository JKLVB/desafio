package com.example.demo.entity;

import java.util.List;


import javax.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "Preço")
    private double valor;

    @Column(name = "Código")
    private int codigo;

    @Column(name = "name")
    private String nome;

    @Column(name = "Categoria")
    private String categoria;

    @Column(name = "Status")
    private String status;

    public Produto() {
    	
    };

    public Produto(double valor, int codigo, String nome, String categoria, String status) {
        super();
        this.valor = valor;
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tutorial [Id=" + id + ", Nome=" + nome + ", Categoria=" + categoria + ", Preço=" + valor + "Código=" + codigo + ", Status=" + status + "]";
    }


}