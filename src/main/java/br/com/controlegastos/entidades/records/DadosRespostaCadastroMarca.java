package br.com.controlegastos.entidades.records;

public record DadosRespostaCadastroMarca(
        long idMarca,
        String mensagem,
        boolean cadastrou
) {
}
