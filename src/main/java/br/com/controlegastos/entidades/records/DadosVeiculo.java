package br.com.controlegastos.entidades.records;

public record DadosVeiculo(
        long idVeiculo,
        String placa,
        String modelo,
        String marca,
        String categoria,
        float quilometragem,
        String tipoCombustivel
) {
}
