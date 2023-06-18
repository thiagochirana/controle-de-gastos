package br.com.controlegastos.entidades.records;

import java.util.Date;

public record DadosRegistroDeGasto(
        String descricao,
        String data,
        double valor,
        long veiculoId,
        long categoriaId
) {
}
