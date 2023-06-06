package br.com.controlegastos.entidades.records;

public record DadosCadastroProprietario(
        String cpf,
        String nome,
        String telefone,
        String email,
        String cnh,
        String categoriaCnh
) {
}
