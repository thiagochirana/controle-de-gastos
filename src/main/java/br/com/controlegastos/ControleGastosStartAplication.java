package br.com.controlegastos;

import br.com.controlegastos.persistencia.versionamento.FlywayImpl;
import br.com.controlegastos.util.CriarDatabase;

public class ControleGastosStartAplication {
    public static void main(String[] args) throws Exception {
        CriarDatabase.start();
        FlywayImpl.start();
    }
}
