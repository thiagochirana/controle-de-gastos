package br.com.controlegastos.persistencia.propriedades;

import java.util.Map;

public class Propriedade {

    String chave;
    String valor;

    Map<String, String> listagem;

    public Propriedade(String chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public Propriedade(){}

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Map<String, String> getListagem() {
        return listagem;
    }

    public void setListagem(Map<String, String> listagem) {
        this.listagem = listagem;
    }

    public void put(String chave, String valor) {
        listagem.put(chave, valor);
    }
}
