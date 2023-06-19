package br.com.controlegastos.controle;

import br.com.controlegastos.entidades.Gastos;
import br.com.controlegastos.entidades.Veiculo;
import br.com.controlegastos.entidades.records.DadosGastos;
import br.com.controlegastos.entidades.records.DadosListaGastoCategoria;
import br.com.controlegastos.entidades.records.DadosRegistroDeGasto;
import br.com.controlegastos.entidades.records.DadosRespostaRegistroGasto;
import br.com.controlegastos.persistencia.service.GastosService;
import br.com.controlegastos.persistencia.service.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GastosController {

    private static Logger LOG = LoggerFactory.getLogger(GastosController.class);

    GastosService gasto = new GastosService();
    
    VeiculoService veiculo = new VeiculoService();

    public GastosController() {
    }

    public DadosRespostaRegistroGasto registrarGasto(DadosRegistroDeGasto dados) throws Exception{
        return gasto.registrarGasto(dados);
    }

    public DadosRespostaRegistroGasto deletarGastoById(long id){
        return gasto.deletarGastoById(id);
    }

    public List<DadosListaGastoCategoria> listaGastosCategoriaPorData(String dataInicial, String dataFinal){
        return gasto.listaGastosCategoriaPorData(dataInicial, dataFinal);
    }

    public List<DadosGastos> listarGastosDeVeiculoEspecifico(long idVeiculo, String dataInicial, String dataFinal) throws Exception{
        
        Veiculo ve = veiculo.obterDadosVeiculoById(idVeiculo);
        if (ve == null) {
            throw new Exception ("id "+idVeiculo+" não existe cadastrado localmente");
        }
        
        return gasto.listarGastosDeVeiculoEspecifico(ve, dataInicial, dataFinal);
    }

    public List<Gastos> listarTodosGastosByDataInicialFinal(boolean ativo, String dataInicial, String dataFinal) {
        return gasto.listarTodosGastosByDataInicialFinal(ativo, dataInicial, dataFinal);
    }
}
