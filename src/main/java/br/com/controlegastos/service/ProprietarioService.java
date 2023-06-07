package br.com.controlegastos.service;

import br.com.controlegastos.entidades.records.DadosCadastroProprietario;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroProprietario;
import br.com.controlegastos.persistencia.database.Executador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;

public class ProprietarioService {

    private static Logger LOG = LoggerFactory.getLogger(ProprietarioService.class);

    public ProprietarioService() {
    }

    public boolean verificaSeExisteProprietario() throws Exception{
        LOG.info("Irei verificar se existe algum proprietario no banco local.");
        try{
            String query = "SELECT p FROM Proprietario p";
            ResultSet rs = Executador.obterResultado(query);
            int row = rs.getRow();
            return (row > 0);
        }catch (Exception e){
            LOG.error("Houve erro em tentar verificar se existe Proprietario já cadastrado localmente", e);
        }
        return false;
    }

    public DadosRespostaCadastroProprietario cadastrarProprietario(DadosCadastroProprietario dados) throws Exception{
        try{
            LOG.info("Vou tentar persistir proprietario de nome "+dados.nome()+ " e CPF "+dados.cpf());
            StringBuilder sql = new StringBuilder();
            String nome = dados.nome();
            sql.append("INSERT INTO Proprietario (id_proprietario,cpf,nome,telefone,email,cnh,categoria_cnh) VALUES (");

            sql.append("DEFAULT, '");
            sql.append(dados.cpf()).append("' , '");
            sql.append(dados.nome()).append("' , '");
            sql.append(dados.telefone()).append("' , '");
            sql.append(dados.email()).append("' , '");
            sql.append(dados.cnh()).append("' , '");
            sql.append(dados.categoriaCnh()).append("' );");

            ResultSet rs = Executador.obterResultado(sql.toString());

            boolean result = rs.next();

            if(result){
                LOG.info("Proprietario "+nome+" cadastrado com sucesso.");
                return new DadosRespostaCadastroProprietario(nome,true,"Proprietario "+nome+" cadastrado com sucesso.");
            } else {
                throw new Exception("Houve um erro ao cadastrar o proprietario "+nome);
            }

        }catch (Exception e){
            LOG.error("Houve um erro ao cadastrar proprietario de nome "+dados.nome(),e);
            return new DadosRespostaCadastroProprietario(dados.nome(),false,"Houve um erro ao cadastrar proprietario de nome "+dados.nome()+". "+e.getMessage());
        }
    }
}
