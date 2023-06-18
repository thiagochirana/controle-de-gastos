package br.com.controlegastos.persistencia.service;

import br.com.controlegastos.entidades.Proprietario;
import br.com.controlegastos.entidades.records.DadosCadastroProprietario;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroProprietario;
import br.com.controlegastos.persistencia.database.ConexaoDB;
import br.com.controlegastos.persistencia.database.Executador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProprietarioService {

    private static Logger LOG = LoggerFactory.getLogger(ProprietarioService.class);

    private static Connection con;

    static {
        try {
            con = ConexaoDB.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProprietarioService() {
    }

    public boolean verificaSeExisteProprietario() throws Exception{
        LOG.info("Irei verificar se existe algum proprietario no banco local.");
        try{
            String query = "SELECT * FROM Proprietario";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = Executador.obterResultado(ps);
            if (rs.next()) {
                LOG.info("Encontrado um proprietário cadastrado localmente");
                return true;
            } else {
                LOG.warn("Nenhum proprietário encontrado salvo localmente. Deve cadastrar um proprietario.");
                return false;
            }
        }catch (Exception e){
            LOG.error("Houve erro em tentar verificar se existe Proprietario já cadastrado localmente", e);
        }
        return false;
    }

    public Proprietario obterProprietario() throws Exception{
        try{
            LOG.info("Obtendo os dados do proprietario atual");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Proprietario");
            ResultSet rs = Executador.obterResultado(ps);

            if (rs.next()){
                return new Proprietario(
                       rs.getLong("id_proprietario"),
                       rs.getString("cpf"),
                       rs.getString("nome"),
                       rs.getString("telefone"),
                       rs.getString("email"),
                       rs.getString("cnh"),
                       rs.getString("categoria_cnh")
                );

            } else {
                LOG.info("Nenhum proprietario encontrado, vou retonar null");
                return null;
            }
        } catch (Exception e){
            LOG.error("Houve um erro ao tentar obter o proprietário local.",e);
            return null;
        }
    }

    public DadosRespostaCadastroProprietario cadastrarProprietario(DadosCadastroProprietario dados) throws Exception{

        if(dados.nome() == null || dados.cpf() == null || dados.telefone() == null || dados.email() == null || dados.cnh() == null || dados.categoriaCnh() == null){
            try{
                LOG.info("Vou persistir o proprietario dos veículos");
                String sql = "INSERT INTO Proprietario (cpf,nome,telefone,email,cnh,categoria_cnh)" +
                        " VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, dados.cpf());
                ps.setString(2, dados.nome());
                ps.setString(3, dados.telefone());
                ps.setString(4, dados.email());
                ps.setString(5, dados.cnh());
                ps.setString(6, dados.categoriaCnh());

                int id = Executador.insertUpdateNoBanco(ps);
                if (id > 0){
                    LOG.info("Proprietario "+dados.nome()+" cadastrado com sucesso.");
                    return new DadosRespostaCadastroProprietario(dados.nome(),true,"Proprietario "+dados.nome()+" cadastrado com sucesso.");
                } else {
                    LOG.warn("Proprietario "+dados.nome()+" não foi cadastrado.");
                    return new DadosRespostaCadastroProprietario(dados.nome(),false,"Proprietario "+dados.nome()+" não foi cadastrado. Verifique o porquê.");
                }
            } catch (Exception e){
                LOG.error("Houve um erro ao tentar cadastrar novo proprietário.",e);
                return new DadosRespostaCadastroProprietario(dados.nome(),false,"Proprietario "+dados.nome()+" não foi cadastrado. Motivo: "+e.getMessage());
            }

        }else {
            LOG.error("Não possui dados para inserir ");
            throw new Exception("Não possuo dados para inserir");
        }
    }
}
