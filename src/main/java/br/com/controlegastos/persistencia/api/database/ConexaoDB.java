package br.com.controlegastos.persistencia.api.database;

import br.com.controlegastos.persistencia.propriedades.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDB {

    private static Connection con;
    private static Logger LOG = LoggerFactory.getLogger(ConexaoDB.class);
    private static Connection realizarConexao() throws Exception{
        try{
            String banco = Config.obterPropriedade("persistencia.banco");
            StringBuilder sb = new StringBuilder();
                sb.append(Config.obterPropriedade("persistencia.url"));
                sb.append(banco);

            String usuario = Config.obterPropriedade("persistencia.usuario");
            String senha = Config.obterPropriedade("persistencia.senha");
            con = DriverManager.getConnection(sb.toString(), usuario, senha);
            LOG.info("Êxito em realizar conexao com o banco "+banco);
            return con;
        }catch (Exception e){
            LOG.error("Ouve um erro ao criar a conexao. "+e);
            throw new Exception("Ouve um erro ao criar a conexao. "+e);
        }
    }

    public static Connection conectar() throws Exception{
        if (con == null){
            realizarConexao();
        }
        return con;
    }

    public static void desconectar() throws Exception{
        String banco = con.getMetaData().getDatabaseProductName();
        con.commit();
        con.close();
        LOG.debug("Encerrado as conexoes com o banco "+banco);
    }
}
