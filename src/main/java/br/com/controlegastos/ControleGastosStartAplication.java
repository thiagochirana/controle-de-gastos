package br.com.controlegastos;

import br.com.controlegastos.persistencia.versionamento.FlywayImpl;

public class ControleGastosStartAplication {
    public static void main(String[] args) throws Exception {
        FlywayImpl.start();
    }
}
