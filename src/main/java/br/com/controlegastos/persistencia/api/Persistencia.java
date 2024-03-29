package br.com.controlegastos.persistencia.api;

import java.sql.ResultSet;
import java.util.List;

public interface Persistencia<Classe>{
    Object buscarById(long id);

    void inserir(Object objeto);

    List listar();

    void deletar(long id);

    ResultSet obterResultadoCru(String queryPronta) throws Exception;
}
