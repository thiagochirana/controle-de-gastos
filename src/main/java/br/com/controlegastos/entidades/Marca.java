package br.com.controlegastos.entidades;

public class Marca {

    private long idMarca;
    private String nome;
    private byte[] logotipoImg;

    public Marca(long idMarca, String nome, byte[] logotipoImg) {
        this.idMarca = idMarca;
        this.nome = nome;
        this.logotipoImg = logotipoImg;
    }

    public Marca(String nome, byte[] logotipoImg) {
        this.nome = nome;
        this.logotipoImg = logotipoImg;
    }

    public long getIdMarca() {
        return idMarca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getLogotipoImg() {
        return logotipoImg;
    }

    public void setLogotipoImg(byte[] logotipoImg) {
        this.logotipoImg = logotipoImg;
    }
}
