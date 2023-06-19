package br.com.controlegastos.controle;

import br.com.controlegastos.entidades.Marca;
import br.com.controlegastos.entidades.Modelo;
import br.com.controlegastos.entidades.records.DadosCadastroMarca;
import br.com.controlegastos.entidades.records.DadosCadastroModelo;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroModelo;
import br.com.controlegastos.persistencia.service.MarcaService;
import br.com.controlegastos.persistencia.service.ModeloService;
import br.com.controlegastos.util.Arquivos;
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

            //Verificar se nomes estão corretos
            if (dados.nome() == null){
                return new DadosRespostaCadastroModelo(-1,
                        "Nome para modelo nulo não é válido",
                        false);
            }

            if (dados.marcaNome() == null){
                return new DadosRespostaCadastroModelo(-1,
                        "Nome para modelo nulo não é válido",
                        false);
            }

            String nomeMarca = dados.marcaNome().toUpperCase().trim();
            String nomeModelo = dados.nome().toUpperCase().trim();
            Marca m;


            //Verificar se marca existe
            LOG.info("Vou verificar se marca "+nomeMarca+" existe");
            try {
                if (marca.verificarSeMarcaExiste(nomeMarca)){
                    m = marca.obterMarcaByNome(nomeMarca);
                    LOG.info("Marca de nome "+nomeMarca+" existe e será usado para salvar este modelo");
                } else {
                    m = marca.cadastrarMarcaEObterMarcaCadastrada(new DadosCadastroMarca(
                            nomeMarca,
                            null
                    ));
                }
            } catch (Exception e){
                LOG.error("Marca "+nomeMarca+" não existe. Vou buscar persistir o mesmo no banco local");
                m = marca.cadastrarMarcaEObterMarcaCadastrada(new DadosCadastroMarca(
                        nomeMarca,
                        null
                ));
                LOG.info("Marca obtida: "+m.toString()+". Irá ser usada para persistir o modelo " +dados.nome());
            }

            //Verificar se Modelo já existe cadastrado com essa Marca. Se sim retornar o mesmo.
            try{
                boolean achou = model.verificarSeModeloCadastradoParaEstaMarca(nomeModelo, m.getIdMarca());
                if (achou){
                    LOG.info("Como existe modelo já cadastrado com esta marca, vou retornar a mesma ao cliente");
                    Modelo mod = model.obterModelo(nomeModelo, m.getIdMarca());
                    return new DadosRespostaCadastroModelo(mod.getIdModelo(),
                            "Modelo "+mod.getNome()+" já cadastrado com sucesso.",
                            false);
                }
            }catch (Exception e){
                LOG.error("Modelo "+nomeModelo+" não existe persistido localmente");
            }

            //Verificar se tem algo no caminho da imagem e se sim verificar se é um arquivo válido
            try {
                boolean existe = Arquivos.verficaSeArquivoExiste(dados.caminhoImagem());
                if (existe){
                    existe = Arquivos.isImagem(dados.caminhoImagem());
                    if (!existe){
                        LOG.error("Dados caminho " + dados.caminhoImagem()+" não é válido");
                        throw new Exception("Dados caminho " + dados.caminhoImagem()+" não é válido");
                    } else {
                        LOG.info("Dados caminho " + dados.caminhoImagem()+ " é valido, logo vou continuar com a persistencia do modelo " + dados.nome());
                    }
                } else {
                    LOG.info("Modelo sem imagem de exemplo.");
                }
            } catch (Exception e){
                LOG.error("Houve um erro ao validar se caminho da imagem está corretamente descrito para cadastro. ",e);
                return new DadosRespostaCadastroModelo(
                        -1,
                        e.getMessage(),
                        false
                );
            }

            //preparar os dados para cadastro de Modelo com marca já devidamente inserido
            DadosCadastroModelo dadosModel = new DadosCadastroModelo(
                    nomeModelo,
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
        LOG.info("Solicitado buscar listagem de modelos já cadastrados. Vou buscar a lista e retornar ao cliente.");
        List<Modelo> lista = model.listaModelos();
        LOG.info("Quantidade de modelos cadastrados e listados: "+lista.size());
        return lista;
    }

    public List<Modelo> listaModeloByIdMarca(long id) throws Exception{
        LOG.info("Solicitado buscar listagem de modelos já cadastrados com o id "+id+". Vou buscar a lista e retornar ao cliente.");
        return model.listaModeloByIdMarca(id);
    }
}
