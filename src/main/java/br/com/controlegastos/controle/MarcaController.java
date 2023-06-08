package br.com.controlegastos.controle;

import br.com.controlegastos.entidades.Marca;
import br.com.controlegastos.entidades.records.DadosCadastroMarca;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroMarca;
import br.com.controlegastos.service.MarcaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class MarcaController {

    private static Logger LOG = LoggerFactory.getLogger(MarcaController.class);

    private MarcaService marca;

    public MarcaController() {
        this.marca = new MarcaService();
    }

    public DadosRespostaCadastroMarca cadastrarMarca(DadosCadastroMarca dados) throws Exception{
        try{
            String nome = dados.nome();
            String caminho = dados.caminhoArquivo();;

            //Verifica se nome está vazio
            if (nome == null || nome.equals("") || nome.length() == 0){
                LOG.warn("Marca sem nome não pode ser cadastrada pois nome está vazio. Vou retornar ao cliente a informação");
                return new DadosRespostaCadastroMarca(
                        -1,
                        "Marca sem nome não pode ser cadastrada pois nome está vazio. Preencha o campo.",
                        false
                );
            } else {
                nome = nome.trim();
            }

            //Vetifica se caminho do arquivo é de fato um arquivo válido
            try{
                if (caminho != null || caminho.length() > 0){
                    try{
                        File arquivo = new File(caminho);
                        if (!arquivo.isFile()){
                            LOG.warn("Caminho inválido para um arquivo :" + caminho+". vou retornar ao cliente essa informação.");
                            return new DadosRespostaCadastroMarca(
                                    -1,
                                    "Arquivo de caminho "+caminho+" inválido. Por favor especifique um arquivo válido, caso queira.",
                                    false
                            );
                        } else {
                            caminho = caminho.trim();
                        }
                    }catch (Exception e){
                        LOG.error("Não foi possível identificar se caminho "+caminho+" é um arquivo ou não",e);
                    }
                }
            } catch (NullPointerException e){
                LOG.warn("Caminho do arquivo logotipo da Marca é nulo.");
            }

            return marca.cadastrarMarca(new DadosCadastroMarca(nome,caminho));
        }catch (Exception e){
            LOG.error("Erro em cadastrar marca "+ dados.nome(),e);
            throw e;
        }
    }

    public List<Marca> listarMarcas() throws Exception{
        return marca.listarMarcas();
    }


}
