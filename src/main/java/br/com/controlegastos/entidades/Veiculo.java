package br.com.controlegastos.entidades;

public class Veiculo {

    private long idVeiculo;
    private String placa;
    private byte[] foto;
    private String tipoCombustivel;
    private float quilometragem;
    private String categoriaVeiculo;
    private long modeloId;
    private long proprietarioId;

    public Veiculo(long idVeiculo, String placa, byte[] foto, String tipoCombustivel, float quilometragem, String categoriaVeiculo, long modeloId, long proprietarioId) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.foto = foto;
        this.tipoCombustivel = tipoCombustivel;
        this.quilometragem = quilometragem;
        this.categoriaVeiculo = categoriaVeiculo;
        this.modeloId = modeloId;
        this.proprietarioId = proprietarioId;
    }

    public Veiculo(String placa, byte[] foto, String tipoCombustivel, float quilometragem, String categoriaVeiculo, long modeloId, long proprietarioId) {
        this.placa = placa;
        this.foto = foto;
        this.tipoCombustivel = tipoCombustivel;
        this.quilometragem = quilometragem;
        this.categoriaVeiculo = categoriaVeiculo;
        this.modeloId = modeloId;
        this.proprietarioId = proprietarioId;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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
