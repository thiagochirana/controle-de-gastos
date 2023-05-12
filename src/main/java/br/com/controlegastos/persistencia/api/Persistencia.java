package br.com.controlegastos.persistencia.api;

import java.util.List;

public interface Persistencia<Objeto> {
    Object buscar(long id);

    void inserir(Objeto objeto);

    List listar();

    void deletar(Objeto objeto);
}
