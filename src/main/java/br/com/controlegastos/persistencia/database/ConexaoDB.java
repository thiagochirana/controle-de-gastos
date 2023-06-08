package br.com.controlegastos.persistencia.database;

import br.com.controlegastos.persistencia.propriedades.Propriedade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDB {

    private static Connection con;
    private static Logger LOG = LoggerFactory.getLogger(ConexaoDB.class);
    private static Connection realizarConexao() throws Exception{
        try{
            String banco = Propriedade.getValor("persistencia.banco");
            StringBuilder sb = new StringBuilder();
                sb.append(Propriedade.getValor("persistencia.url"));
                sb.append(banco);

            String usuario = Propriedade.getValor("persistencia.usuario");
            String senha = Propriedade.getValor("persistencia.senha");
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
            LOG.warn("Conexão com banco não criada/inexistente. Vou criar um e retornar ao cliente.");
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
