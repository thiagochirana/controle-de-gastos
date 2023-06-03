package br.com.controlegastos.persistencia.api.controller;

import br.com.controlegastos.persistencia.api.Persistencia;
import br.com.controlegastos.persistencia.api.persistencia.interpretador.Interpretador;

import java.sql.ResultSet;
import java.util.List;

public class Controller<Classe> implements Persistencia {

    private Interpretador interpretador = new Interpretador();

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
    public void deletar(Object objeto) {

    }


    @Override
    public ResultSet obterResultadoCru(String queryPronta) throws Exception {
        return interpretador.obterResultadoCru(queryPronta);
    }

}
