package br.com.controlegastos.util;

import br.com.controlegastos.persistencia.propriedades.Propriedade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CriarDatabase {

    private static Connection con;

    private static Logger LOG = LoggerFactory.getLogger(CriarDatabase.class);

    public static void start() throws Exception {
        try{
            String banco = "postgres";
            StringBuilder sb = new StringBuilder();
            sb.append(Propriedade.getValor("persistencia.url"));
            sb.append(banco);

            String usuario = "postgres";
            String senha = "postgres";
            con = DriverManager.getConnection(sb.toString(), usuario, senha);
            LOG.info("Êxito em realizar conexão com o banco standart "+banco+". Vou verificar a existência do banco principal  "+Propriedade.getValor("persistencia.banco"));


            //Tentativa de acesso ao banco da aplicação, para verificar se ele existe ou não
            usuario = Propriedade.getValor("persistencia.usuario");
            senha = Propriedade.getValor("persistencia.senha");
            banco = Propriedade.getValor("persistencia.banco");
            String url = Propriedade.getValor("persistencia.url");

            LOG.info("Vou buscar me conectar no banco "+banco);
            try (Connection conec = DriverManager.getConnection(url+banco, usuario, senha);
                 Statement statement = conec.createStatement()) {

                String checkDatabaseQuery = "SELECT 1";
                ResultSet resultSet = statement.executeQuery(checkDatabaseQuery);

                if (resultSet.next()) {
                    LOG.info("Banco de dados existe. Logo, não será necessário executar um CREATE DATABASE "+banco);
                } else {
                    LOG.warn("Banco de dados não existe, vou buscar criar o banco "+banco);

                    try{
                        Statement stm = con.createStatement();
                        String createDb = "CREATE DATABASE "+banco;
                        ResultSet rs = stm.executeQuery(createDb);
                        if (rs.next()) {
                            LOG.info("Banco de dados "+banco+" criado com sucesso.");
                        } else {
                            LOG.error("Não foi possível criar banco de dados de nome "+banco+", será necessário ser criado manualmente antes da aplicação iniciar.");
                        }
                    }catch (Exception e){
                        throw new Exception("Erro ao criar banco de dados",e);
                    }
                }

            } catch (SQLException e) {
                LOG.warn("Banco "+banco+ " não existe. vou tentar criá-lo");
                Statement stm = con.createStatement();
                String createDb = "CREATE DATABASE "+banco;
                boolean executou = stm.execute(createDb);

                if (!executou) {
                    LOG.info("Banco de dados "+banco+" criado com sucesso.");
                } else {
                    LOG.error("Não foi possível criar banco de dados de nome "+banco+", será necessário ser criado manualmente antes da aplicação iniciar.");
                    throw e;
                }
            }

        }catch (Exception e){
            throw e;
        }
    }
}
