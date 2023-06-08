package br.com.controlegastos.entidades;

import br.com.controlegastos.entidades.records.DadosCadastroProprietario;

public class Proprietario {

    private long id_proprietario;

    private String cpf;

    private String nome;

    private String telefone;

    private String email;

    private String cnh;

    private String categoriaCnh;

    //Todos os dados
    public Proprietario(long id_proprietario, String cpf, String nome, String telefone, String email, String cnh,String categoriaCnh) {
        this.id_proprietario = id_proprietario;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cnh = cnh;
        this.categoriaCnh = categoriaCnh;
    }

    //Sem ID
    public Proprietario(String cpf, String nome, String telefone, String email, String cnh,String categoriaCnh) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cnh = cnh;
        this.categoriaCnh = categoriaCnh;
    }

    public Proprietario(DadosCadastroProprietario dados){
        this.cpf = dados.cpf();
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.cnh = dados.cnh();
        this.categoriaCnh = dados.categoriaCnh();
    }

    public long getId_proprietario() {
        return id_proprietario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
}
