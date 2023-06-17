package br.com.controlegastos.controle;

import br.com.controlegastos.entidades.Veiculo;
import br.com.controlegastos.entidades.records.DadosCadastroVeiculo;
import br.com.controlegastos.entidades.records.DadosRespostaVeiculo;
import br.com.controlegastos.service.MarcaService;
import br.com.controlegastos.service.ModeloService;
import br.com.controlegastos.service.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VeiculoController {

    private static Logger LOG = LoggerFactory.getLogger(VeiculoController.class);

    MarcaService marca = new MarcaService();

    ModeloService model = new ModeloService();

    VeiculoService veiculo = new VeiculoService();

    public DadosRespostaVeiculo cadastrarVeiculo(DadosCadastroVeiculo dados) throws Exception{
        return veiculo.cadastrarVeiculo(dados);
    }

    public DadosRespostaVeiculo ativaDesativaVeiculo(long id, boolean atividade) throws Exception{
        return veiculo.ativaDesativaVeiculo(id, atividade);
    }

    public boolean verificaCadastroPlaca(String placa) throws Exception{
        return veiculo.verificaCadastroPlaca(placa);
    }

    public List<Veiculo> listarVeiculos(boolean atividade) throws Exception{
        return veiculo.listarVeiculos(atividade);
    }

    public List<Veiculo> listarVeiculos() throws Exception{
        return veiculo.listarVeiculos();
    }
}
