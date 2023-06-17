package br.com.controlegastos.service;

import br.com.controlegastos.entidades.Veiculo;
import br.com.controlegastos.entidades.records.DadosCadastroVeiculo;
import br.com.controlegastos.entidades.records.DadosRespostaVeiculo;
import br.com.controlegastos.persistencia.database.ConexaoDB;
import br.com.controlegastos.persistencia.database.Executador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoService {

    private static Logger LOG = LoggerFactory.getLogger(VeiculoService.class);

    private static Connection con;

    static {
        try {
            con = ConexaoDB.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public VeiculoService() {}

    public Veiculo obterDadosVeiculoById(long id) throws Exception{
        try{
            LOG.info("Vou buscar o veículo de ID "+id);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Veiculo WHERE idVeiculo =?");
            ps.setLong(1, id);

            ResultSet rs = Executador.obterResultado(ps);

            if(rs.next()){
                Veiculo ve = new Veiculo(
                        rs.getLong("idVeiculo"),
                        rs.getBoolean("temPlaca"),
                        rs.getString("placa"),
                        rs.getString("tipoCombustivel"),
                        rs.getFloat("quilometragem"),
                        rs.getString("categoriaVeiculo"),
                        rs.getLong("modeloId"),
                        rs.getLong("proprietarioId"),
                        rs.getBoolean("ativo")
                );
                LOG.info("Resultado obtido: "+ve+". Vou retornar o cliente.");
                return ve;
            } else {
                LOG.warn("Nenhum veículo encontrado com o id "+id);
                throw new Exception("Nenhum veículo encontrado com o id "+id);
            }
        } catch (Exception e){
            LOG.error("Houve um erro ao tentar realizar a consulta ao veículo de id "+id,e);
            throw e;
        }
    }


    public DadosRespostaVeiculo cadastrarVeiculo(DadosCadastroVeiculo dados) throws Exception{
        String placa = dados.placa();
        LOG.info("Irei buscar cadastrar Veículo de placa");
        try{
            placa = placa.trim().toUpperCase();

            PreparedStatement ps = con.prepareStatement("INSERT INTO Veiculo" +
                    "(temPlaca,placa,tipoCombustivel,quilometragem,categoriaVeiculo,ativo,modeloId,proprietarioId)" +
                    "VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setBoolean(1, dados.temPlaca());
            ps.setString(2, dados.temPlaca() ? placa : "SEM PLACA");
            ps.setString(3, dados.tipoCombustivel());
            ps.setFloat(4, dados.quilometragem());
            ps.setString(5, dados.categoriaVeiculo());
            ps.setBoolean(6, true); //Por padrão qualquer veículo cadastrado significa que é novo
            ps.setLong(7, dados.modeloId());
            ps.setLong(8, dados.proprietarioId());

            long id = Executador.insertUpdateNoBanco(ps);

            LOG.info("Cadastro de veículo realizado com sucesso com o id "+id+"! Vou retornar a informacao ao Cliente.");

            DadosRespostaVeiculo resposta = new DadosRespostaVeiculo(
                    id,
                    "Veículo cadastrado com sucesso!",
                    true
            );
            return resposta;
        }catch (Exception e){
            LOG.error("Hovue um erro ao tentar cadastrar o veículo");
            return new DadosRespostaVeiculo(
                    -1,
                    "Erro ao tentar cadastrar o veículo",
                    false
            );
        }
    }

    public DadosRespostaVeiculo ativaDesativaVeiculo(long id, boolean atividade) throws Exception{
        try{
            LOG.info("Irei "+ (atividade ? "ativar": "desativar") +" veiculo de id "+id);

            PreparedStatement ps = con.prepareStatement("UPDATE Veiculo SET ativo = ? WHERE idVeiculo = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, atividade);
            ps.setLong(2, id);
            int idObtido = Executador.insertUpdateNoBanco(ps);
            if (idObtido == id) {
                LOG.info("Veículo de id "+id+" "+ (atividade ? "ativado": "desativado") +" com sucesso!");
                return new DadosRespostaVeiculo(
                        id,
                        "Veículo de id "+id+" "+ (atividade ? "ativado": "desativado") +" com sucesso!",
                        true);
            } else {
                throw new Exception("Veículo de id "+id+" não foi "+ (atividade ? "ativado": "desativado"));
            }
        } catch (Exception e){
            LOG.info("Houve um erro ao tentar "+ (atividade ? "ativar": "desativar") +" veículo de id "+id, e);
            return new DadosRespostaVeiculo(
                    id,
                    "Houve um erro ao tentar "+ (atividade ? "ativar": "desativar") +" veículo de id "+id+". "+ e.getMessage(),
                    false);
        }
    }

    //Listar veículo de acordo com a atividade do mesmo
    public List<Veiculo> listarVeiculos(boolean atividade) throws Exception{
        try{
            List<Veiculo> lista = new ArrayList<>();
            LOG.info("Irei listar todos os veículos"+ (atividade ? "ativos": "desativados") +" cadastrados");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Veiculo WHERE ativo =? ", Statement.RETURN_GENERATED_KEYS);
            return getVeiculos(lista, ps);
        } catch ( Exception e){
            LOG.error("Houve um erro ao listar veículos "+ (atividade ? "ativos": "desativados"),e);
            throw e;
        }
    }

    //Listar todos os veículos já cadastrados
    public List<Veiculo> listarVeiculos() throws Exception{
        try{
            List<Veiculo> lista = new ArrayList<>();
            LOG.info("Irei listar todos os veículos cadastrados");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Veiculo", Statement.RETURN_GENERATED_KEYS);
            return getVeiculos(lista, ps);
        } catch ( Exception e){
            LOG.error("Houve um erro ao listar todos os veículos ",e);
            throw e;
        }
    }


    //Utils da classe
    public boolean verificaCadastroPlaca(String placa) throws Exception{
        placa = placa.trim().toUpperCase();
        try{
            LOG.info("Verificarei se placa já existe cadastrada");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Veiculo WHERE placa = ? ", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, placa);
            boolean result = Executador.obterResultado(ps).next();
            if (result) {
                LOG.info("Veículo de placa "+placa+" já está cadastrada");
                return true;
            } else {
                LOG.info("Veículo de placa "+placa+" não encontrado");
                return false;
            }
        }catch (Exception e){
            LOG.info("Houve um erro ao tentar buscar Veículo pela placa "+placa,e);
            throw new Exception("Erro ao tentar buscar veículo de placa "+placa);
        }
    }

    private List<Veiculo> getVeiculos(List<Veiculo> lista, PreparedStatement ps) throws SQLException {
        ResultSet rs = Executador.obterResultado(ps);
        while (rs.next()) {
            lista.add(new Veiculo(
                    rs.getLong("idVeiculo"),
                    rs.getBoolean("temPlaca"),
                    rs.getString("placa"),
                    rs.getString("tipoCombustivel"),
                    rs.getFloat("quilometragem"),
                    rs.getString("categoriaVeiculo"),
                    rs.getLong("modeloId"),
                    rs.getLong("proprietarioId"),
                    rs.getBoolean("ativo")
            ));
        }
        return lista;
    }
}
