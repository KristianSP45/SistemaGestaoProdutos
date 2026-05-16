package com.example.sistemagestaoprodutos;

public class Produtos {

    private  int idProdutos;
    private String nome;
    private Double valor;
    private Double quantidade;

    public Produtos(String nome, Double valor, Double quantidade) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Produtos(int idProdutos, String nome, Double valor, Double quantidade) {
        this.idProdutos = idProdutos;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public int getIdProdutos() {
        return idProdutos;
    }

    public void setIdProdutos(int idProdutos) {
        this.idProdutos = idProdutos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
