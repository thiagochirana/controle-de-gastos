package br.com.controlegastos.service;

import br.com.controlegastos.entidades.records.DadosLogin;
import br.com.controlegastos.entidades.records.DadosRespostaLogin;
import br.com.controlegastos.persistencia.database.ConexaoDB;
import br.com.controlegastos.persistencia.database.Executador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AcessoService {

    private static Logger LOG = LoggerFactory.getLogger(AcessoService.class);

    private static Connection con;

    static {
        try {
            con = ConexaoDB.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AcessoService() {
    }

    public DadosRespostaLogin verificaSeTemCadastro(DadosLogin dados) throws Exception{
        try{
            LOG.info("Vou buscar se Login existe ou se está correto");
            boolean acessado = false;
            PreparedStatement stm = con.prepareStatement("SELECT l FROM Acesso l WHERE 1=1 AND l.login=? AND l.senha=?");
            stm.setString(1, dados.login().trim());
            stm.setString(2, dados.senha().trim());
            ResultSet rs = Executador.obterResultado(stm);
            if (rs.next()) {
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                if (login.equals(dados.login().trim()) && senha.equals(dados.senha().trim())){
                    LOG.info("Login e senha correspondem para acesso "+dados.login()+". Acesso liberado");
                    acessado = true;
                } else {
                    LOG.info("Login e senha não correspondem para acesso "+dados.login()+". Acesso negado");
                    acessado = false;
                }
            } else {
                LOG.info("Não foi encontrado "+dados.login()+". Acesso negado");
                acessado = false;
            }

            if(acessado){
                return new DadosRespostaLogin(
                        "Acesso liberado",
                        true
                );
            } else {
                return new DadosRespostaLogin(
                        "Acesso negado",
                        false
                );
            }
        }catch (Exception e){
            LOG.error("Houve erro ao tentar obter login e senha.",e);
            return new DadosRespostaLogin(
                    "Houve erro ao tentar obter login e senha. Acesso negado",
                    false
            );
        }
    }

}
