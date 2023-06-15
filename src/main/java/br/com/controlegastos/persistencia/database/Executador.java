package br.com.controlegastos.persistencia.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class Executador {

    private static Logger LOG = LoggerFactory.getLogger(Executador.class);

    private static Connection con;

    static {
        try {
            con = ConexaoDB.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet obterResultado(String query) throws SQLException{
        try{
            LOG.debug("Vou realizar a consulta ao banco");
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery(query);
            LOG.debug("Consulta realizada, vou devolver a resposta ao cliente");
            return resultSet;
        } catch (SQLException se){
            LOG.error("Houve um erro ao executar o SQL "+query,se);
            throw se;
        } catch (Exception e) {
            LOG.error("Houve um erro ao realizar a consulta no banco com a query "+query,e);
            throw e;
        }
    }

    public static ResultSet obterResultado(PreparedStatement query) throws SQLException{
        try{
            LOG.info("Vou realizar a consulta ao banco. Query é "+query.toString());
            ResultSet resultSet = query.executeQuery();
            LOG.info("Consulta realizada, vou devolver a resposta ao cliente");
            return resultSet;
        } catch (SQLException se){
            LOG.error("Houve um erro ao executar o SQL "+query,se);
            throw se;
        } catch (Exception e) {
            LOG.error("Houve um erro ao realizar a consulta no banco com a query "+query,e);
            throw e;
        }
    }

    public static int insertUpdateNoBanco(PreparedStatement query) throws Exception{
        try {
            LOG.debug("Vou realizar a inserção/update ao banco.");
            int row = query.executeUpdate();
            LOG.debug("Ação de insert/update no banco executada");
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGerado = generatedKeys.getInt(1);
                LOG.debug("Inserção realizada com sucesso. Linhas afetadas: "+row);
                return idGerado;
            } else {
                return -1;
            }
        }catch (Exception e){
            LOG.error("Não foi possivel realizar insert/update no banco",e);
            throw e;
        }
    }
}
