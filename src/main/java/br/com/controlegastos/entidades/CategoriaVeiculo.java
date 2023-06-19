package br.com.controlegastos.entidades;

public class CategoriaVeiculo {
    private long idCategoriaVeiculo;
    private String nome;

    public CategoriaVeiculo(long idCategoriaVeiculo, String nome) {
        this.idCategoriaVeiculo = idCategoriaVeiculo;
        this.nome = nome;
    }

    public CategoriaVeiculo(String nome) {
        this.nome = nome;
    }

    public CategoriaVeiculo() {}

    public long getIdCategoriaVeiculo() {
        return idCategoriaVeiculo;
    }

    public void setIdCategoriaVeiculo(long idCategoriaVeiculo) {
        this.idCategoriaVeiculo = idCategoriaVeiculo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
