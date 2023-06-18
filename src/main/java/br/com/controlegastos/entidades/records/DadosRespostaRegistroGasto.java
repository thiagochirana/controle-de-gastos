package br.com.controlegastos.entidades.records;

public record DadosRespostaRegistroGasto(
        long id,
        String mensagem,
        double valor,
        boolean cadastrou
) {
}
