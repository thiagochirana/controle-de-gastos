package br.com.controlegastos.entidades;

public class Veiculo {

    private long idVeiculo;
    private boolean temPlaca;
    private String placa;

    private String descricaoVeiculo;

    private String tipoCombustivel;
    private float quilometragem;
    private String categoriaVeiculo;
    private long modeloId;
    private long proprietarioId;
    private boolean ativo;


    public Veiculo(long idVeiculo, boolean temPlaca, String placa, String descricaoVeiculo, String tipoCombustivel, float quilometragem, String categoriaVeiculo, long modeloId, long proprietarioId, boolean ativo) {
        this.idVeiculo = idVeiculo;
        this.temPlaca = temPlaca;
        this.placa = placa;
        this.descricaoVeiculo = descricaoVeiculo;
        this.tipoCombustivel = tipoCombustivel;
        this.quilometragem = quilometragem;
        this.categoriaVeiculo = categoriaVeiculo;
        this.modeloId = modeloId;
        this.proprietarioId = proprietarioId;
        this.ativo = ativo;
    }

    public Veiculo(long idVeiculo, boolean temPlaca, String placa, String tipoCombustivel, float quilometragem, String categoriaVeiculo, long modeloId, long proprietarioId, boolean ativo) {
        this.idVeiculo = idVeiculo;
        this.temPlaca = temPlaca;
        this.placa = placa;
        this.tipoCombustivel = tipoCombustivel;
        this.quilometragem = quilometragem;
        this.categoriaVeiculo = categoriaVeiculo;
        this.modeloId = modeloId;
        this.proprietarioId = proprietarioId;
        this.ativo = ativo;
    }

    public Veiculo(long idVeiculo, String placa, String descricaoVeiculo, String tipoCombustivel, float quilometragem, String categoriaVeiculo, long modeloId, long proprietarioId, boolean ativo) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.descricaoVeiculo = descricaoVeiculo;
        this.tipoCombustivel = tipoCombustivel;
        this.quilometragem = quilometragem;
        this.categoriaVeiculo = categoriaVeiculo;
        this.modeloId = modeloId;
        this.proprietarioId = proprietarioId;
        this.ativo = ativo;
    }

    public Veiculo(String placa, String tipoCombustivel, float quilometragem, String categoriaVeiculo, long modeloId, long proprietarioId, boolean ativo) {
        this.placa = placa;
        this.tipoCombustivel = tipoCombustivel;
        this.quilometragem = quilometragem;
        this.categoriaVeiculo = categoriaVeiculo;
        this.modeloId = modeloId;
        this.proprietarioId = proprietarioId;
        this.ativo = ativo;
    }

    public String getDescricaoVeiculo() {
        return descricaoVeiculo;
    }

    public void setDescricaoVeiculo(String descricaoVeiculo) {
        this.descricaoVeiculo = descricaoVeiculo;
    }

    public boolean isTemPlaca() {
        return temPlaca;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setIdVeiculo(long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public void setTemPlaca(boolean temPlaca) {
        this.temPlaca = temPlaca;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public long getIdVeiculo() {
        return idVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public float getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(float quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getCategoriaVeiculo() {
        return categoriaVeiculo;
    }

    public void setCategoriaVeiculo(String categoriaVeiculo) {
        this.categoriaVeiculo = categoriaVeiculo;
    }

    public long getModeloId() {
        return modeloId;
    }

    public void setModeloId(long modeloId) {
        this.modeloId = modeloId;
    }

    public long getProprietarioId() {
        return proprietarioId;
    }

    public void setProprietarioId(long proprietarioId) {
        this.proprietarioId = proprietarioId;
    }
}
