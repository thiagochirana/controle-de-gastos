package br.com.controlegastos.controle;

import br.com.controlegastos.entidades.records.DadosLogin;
import br.com.controlegastos.entidades.records.DadosRespostaLogin;
import br.com.controlegastos.persistencia.service.AcessoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AcessoController {

    private static Logger LOG = LoggerFactory.getLogger(AcessoController.class);

    private AcessoService acesso;

    public AcessoController() {
        this.acesso = new AcessoService();
    }

    public DadosRespostaLogin verificaSeTemCadastro(DadosLogin dados) throws Exception{

        String login;
        String senha;

        //Se cair no catch, significa que um dos campos está vazio
        try {
            login = dados.login().trim();
            senha = dados.senha().trim();
        } catch (Exception e) {
            LOG.warn("Houve erro ao tentar validar campos login e senha.",e);
            return new DadosRespostaLogin(
                    "Falta login ou senha.",
                    false
            );
        }

        //Verifica tamanho do login e senha
        if(login.length() == 0){
            LOG.warn("Login não existe. Acesso negado");
            return new DadosRespostaLogin(
                    "Falta login. Por favor informe login",
                    false
            );
        }

        if(senha.length() == 0){
            LOG.warn("Senha não existe. Acesso negado");
            return new DadosRespostaLogin(
                    "Faltou uma senha. Por favor informe senha",
                    false
            );
        }

        return acesso.verificaSeTemCadastro(dados);
    }


}
