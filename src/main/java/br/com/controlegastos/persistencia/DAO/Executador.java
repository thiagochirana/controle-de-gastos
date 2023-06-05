package br.com.controlegastos.persistencia.DAO;

import br.com.controlegastos.persistencia.api.database.ConexaoDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            stm.close();
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

}
