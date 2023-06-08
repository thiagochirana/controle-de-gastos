package br.com.controlegastos.entidades;

public class CategoriaGasto {

    private long idCategoria;
    private String nome;

    public CategoriaGasto(long idCategoria, String nome) {
        this.idCategoria = idCategoria;
        this.nome = nome;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
