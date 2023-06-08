package br.com.controlegastos.persistencia.api.controller;

import br.com.controlegastos.persistencia.api.Persistencia;

import java.sql.ResultSet;
import java.util.List;

public class Controller<Classe> implements Persistencia {

    @Override
    public Object buscarById(long id){
        System.out.println(id);
        return null;
    }

    @Override
    public void inserir(Object objeto) {

    }

    @Override
    public List listar(){
        return null;
    }

    @Override
    public void deletar(long objeto) {

    }


    @Override
    public ResultSet obterResultadoCru(String queryPronta) throws Exception {
        return null;
    }

}
