package br.com.controlegastos.entidades.records;

import br.com.controlegastos.entidades.Gastos;

import java.util.List;

public record DadosRelatorioGastosVeiculoUnico(
        String veiculo,
        String modelo,
        String marca,
        String mesAno,
        List<Gastos> listaGastosMensal,
        float valorTotal
) {
}
