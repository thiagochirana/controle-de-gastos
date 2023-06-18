package br.com.controlegastos.controle;

import br.com.controlegastos.entidades.records.DadosCadastroProprietario;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroProprietario;
import br.com.controlegastos.persistencia.service.ProprietarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProprietarioController {

    ProprietarioService propService;
    private static Logger LOG = LoggerFactory.getLogger(ProprietarioController.class);

    public ProprietarioController() {
        this.propService = new ProprietarioService();
    }

    public DadosRespostaCadastroProprietario cadastrarProprietario(DadosCadastroProprietario proprietario) throws Exception {
        try{
            String nome = proprietario.nome();

            //verifica se existe algum proprietario
            if(propService.verificaSeExisteProprietario()){
                LOG.info("Já existe um proprietario");
                return new DadosRespostaCadastroProprietario(proprietario.nome(), false,"Já existe um proprietário salvo no aplicativo.");
            } else {
                LOG.info("Não existe um proprietario. Vamos cadastrar o proprietario "+nome);
            }

            return propService.cadastrarProprietario(proprietario);
        }catch (Exception e){
            return new DadosRespostaCadastroProprietario(proprietario.nome(),false,e.getMessage());
        }
    }

    public boolean verificaSePropriatarioExiste() throws Exception {
        return propService.verificaSeExisteProprietario();
    }

}
