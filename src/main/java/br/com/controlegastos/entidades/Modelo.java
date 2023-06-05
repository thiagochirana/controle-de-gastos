package br.com.controlegastos.entidades;

public class Modelo {

    private long idModelo;
    private String nome;
    private byte[] imagem;
    private long marcaId;

    public Modelo(long idModelo, String nome, byte[] imagem, long marcaId) {
        this.idModelo = idModelo;
        this.nome = nome;
        this.imagem = imagem;
        this.marcaId = marcaId;
    }

    public Modelo(String nome, byte[] imagem, long marcaId) {
        this.nome = nome;
        this.imagem = imagem;
        this.marcaId = marcaId;
    }

    public long getIdModelo() {
        return idModelo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(long marcaId) {
        this.marcaId = marcaId;
    }
}
