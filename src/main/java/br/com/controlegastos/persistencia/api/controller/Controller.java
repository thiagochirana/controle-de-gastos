package br.com.controlegastos.persistencia.api.controller;

import br.com.controlegastos.persistencia.api.Persistencia;

import java.util.List;

public class Controller<Objeto> implements Persistencia {

    @Override
    public Objeto buscar(long id){
        return null;
    }

    @Override
    public void inserir(Object objeto) {
        Object obj = objeto;
    }

    @Override
    public List<Objeto> listar(){
        return null;
    }

    @Override
    public void deletar(Object objeto) {

    }

}
