package br.com.controlegastos.entidades.records;

import java.io.Serializable;

public record DadosCadastroVeiculo(

        boolean temPlaca,
        String placa,
        byte[] foto,
        String tipoCombustivel,
        float quilometragem,
        String categoriaVeiculo,
        long modeloId,
        long proprietarioId
        ) implements Serializable {
}
