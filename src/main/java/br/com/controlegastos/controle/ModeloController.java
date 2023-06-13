package br.com.controlegastos.controle;

import br.com.controlegastos.entidades.Marca;
import br.com.controlegastos.entidades.Modelo;
import br.com.controlegastos.entidades.records.DadosCadastroModelo;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroMarca;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroModelo;
import br.com.controlegastos.service.MarcaService;
import br.com.controlegastos.service.ModeloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ModeloController {

    private static Logger LOG = LoggerFactory.getLogger(ModeloController.class);

    MarcaService marca = new MarcaService();

    ModeloService model = new ModeloService();

    public DadosRespostaCadastroModelo cadastrarModelo(DadosCadastroModelo dados) throws Exception{
        LOG.info("Cadastro de modelo solicitado -> "+dados.toString());
        try{

            Marca m;

            //Verificar se marca existe
            LOG.info("Vou verificar se marca de nome "+dados.marcaNome()+" existe");
            try {
                m = marca.obterMarcaByNome(dados.marcaNome());
                LOG.info("Marca de nome "+dados.marcaNome()+" existe e será usado para salvar este modelo");
            } catch (Exception e){
                LOG.error("Marca não existe.");
                throw new Exception("Marca não existe. "+ e.getMessage());
            }

            //Verificar se tem algo no caminho da imagem e se sim verificar se é um arquivo válido
            try {

            } catch (Exception e){

            }
            //preparar os dados para cadastro de Modelo com marca já devidamente inserido
            DadosCadastroModelo dadosModel = new DadosCadastroModelo(
                    dados.nome(),
                    dados.caminhoImagem(),
                    m.getIdMarca(),
                    m.getNome()
            );
            return model.cadastrarModelo(dadosModel);
        } catch (Exception e){
            LOG.error("Houve um erro ao validar se Modelo está corretamente descrito para cadastro. ",e);
            throw new Exception("Houve um erro ao validar se Modelo está corretamente descrito para cadastro. "+e.getMessage());
        }
    }

    public List<Modelo> listaModelos() throws Exception{
        LOG.info("Solicitado buscar listagem de modelos já cadastrados.");
        return model.listaModelos();
    }
}
