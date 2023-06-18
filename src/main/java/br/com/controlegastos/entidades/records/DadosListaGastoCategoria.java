package br.com.controlegastos.entidades.records;

public record DadosListaGastoCategoria(
        long idCategoriaGasto,
        String nome,
        String descricao,
        String dataInicial,
        String dataFinal,
        double valor
) {
}
