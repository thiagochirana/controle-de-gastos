package br.com.controlegastos.controle;

import br.com.controlegastos.entidades.CategoriaVeiculo;
import br.com.controlegastos.entidades.Marca;
import br.com.controlegastos.entidades.Modelo;
import br.com.controlegastos.entidades.Veiculo;
import br.com.controlegastos.entidades.records.DadosCadastroVeiculo;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroCategoriaVeiculo;
import br.com.controlegastos.entidades.records.DadosRespostaVeiculo;
import br.com.controlegastos.entidades.records.DadosVeiculo;
import br.com.controlegastos.persistencia.service.MarcaService;
import br.com.controlegastos.persistencia.service.ModeloService;
import br.com.controlegastos.persistencia.service.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VeiculoController {

    private static Logger LOG = LoggerFactory.getLogger(VeiculoController.class);

    MarcaService marca = new MarcaService();

    ModeloService model = new ModeloService();

    VeiculoService veiculo = new VeiculoService();

    public DadosRespostaVeiculo cadastrarVeiculo(DadosCadastroVeiculo dados) throws Exception{
        LOG.info("Solicitação para cadastro de veículo iniciada. Dados do veículo: "+dados);

        //Verificar se modelo existe com o ID
        LOG.info("Verificacao para modelo se existe com o id "+dados.modeloId());
        Modelo modelo = model.obterModeloById(dados.modeloId());
        if (modelo == null){
            LOG.info("Modelo não existe na base de dados, logo não haverá cadastro de veículo");
            return new DadosRespostaVeiculo(
                    -1,
                    "Modelo não existe na base de dados, logo não haverá cadastro de veículo",
                    false
            );
        }

        //Verificar se marca existe com o ID
        LOG.info("Verificacao para marca se existe com o id "+modelo.getMarcaId());
        Marca marc = marca.obterMarcaById(modelo.getMarcaId());
        if (marc == null){
            LOG.info("Marca não existe na base de dados, logo não haverá cadastro de veículo");
            return new DadosRespostaVeiculo(
                    -1,
                    "Marca não existe na base de dados, logo não haverá cadastro de veículo",
                    false
            );
        }

        return veiculo.cadastrarVeiculo(dados);
    }

    public DadosRespostaCadastroCategoriaVeiculo cadastrarCategoriaVeiculo(String categoria) throws Exception{

        if (categoria.equals("") || categoria == null){
            return new DadosRespostaCadastroCategoriaVeiculo(
                    -1,
                    categoria,
                    "Não é possível salvar categoria de nome vazio ou inexistente.",
                    false
            );
        }

        if (veiculo.verificarSeCategoriaExisteCadastrada(categoria)){
            return new DadosRespostaCadastroCategoriaVeiculo(
                    0,
                    categoria,
                    "Categoria "+categoria+" já existe cadastrada.",
                    false
            );
        }

        categoria = categoria.trim().toUpperCase();
        return veiculo.cadastrarCategoriaVeiculo(categoria);
    }

    public List<CategoriaVeiculo> listarCategoriaVeiculo(){
        return veiculo.listarCategoriaVeiculo();
    }

    public DadosRespostaVeiculo ativaDesativaVeiculo(long id, boolean atividade) throws Exception{
        //Verificar se ID veiculo existe
        if (!veiculo.verificaSeVeiculoIdExiste(id)){
            LOG.info("Veículo não existe na base de dados, logo não haverá alterações");
            return new DadosRespostaVeiculo(
                    id,
                    "Veículo não existe na base de dados, logo não haverá alterações",
                    false
            );
        }
        return veiculo.ativaDesativaVeiculo(id, atividade);
    }

    public boolean verificaCadastroPlaca(String placa) throws Exception{
        if (placa != null || placa.isEmpty()){
            LOG.info("Placa está nula, logo retornarei false.");
            return false;
        }
        return veiculo.verificaCadastroPlaca(placa);
    }

    public List<String> listarTiposCombustivel(){
        LOG.info("Listando os tipos de combustível e devolver ao cliente");
        return veiculo.listarTiposCombustivel();
    }

    public List<DadosVeiculo> listarVeiculos(boolean atividade) throws Exception{
        return veiculo.listarVeiculos(atividade);
    }

    public List<DadosVeiculo> listarVeiculos() throws Exception{
        return veiculo.listarVeiculos();
    }
}
