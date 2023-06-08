package br.com.controlegastos.entidades;

import java.util.Date;

public class Gastos {

    private long idGastos;
    private String descricao;
    private Date data;
    private double valor;
    private long veiculoId;
    private long categoriaId;

    public Gastos(long idGastos, String descricao, Date data, double valor, long veiculoId, long categoriaId) {
        this.idGastos = idGastos;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.veiculoId = veiculoId;
        this.categoriaId = categoriaId;
    }

    public Gastos(String descricao, Date data, double valor, long veiculoId, long categoriaId) {
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.veiculoId = veiculoId;
        this.categoriaId = categoriaId;
    }

    public long getIdGastos() {
        return idGastos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
