package br.com.controlegastos.entidades.records;

public record DadosRespostaCadastroCategoriaVeiculo(
        long id,
        String nome,
        String mensagem,
        boolean cadastrou
) {
}
