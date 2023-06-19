package br.com.controlegastos.entidades.records;

public record DadosGastos(
        String placaModelo,
        double valor,
        String tipoGasto,
        String dataRegistro
) {
}
