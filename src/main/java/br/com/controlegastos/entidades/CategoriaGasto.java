package br.com.controlegastos.entidades;

public class CategoriaGasto {

    private long idCategoria;
    private String nome;

    String descricaoCategoria;

    public CategoriaGasto(long idCategoria, String nome) {
        this.idCategoria = idCategoria;
        this.nome = nome;
    }

    public CategoriaGasto() {
    }

    public CategoriaGasto(long idCategoria, String nome, String descricaoCategoria) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.descricaoCategoria = descricaoCategoria;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
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
