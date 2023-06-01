package br.com.controlegastos.persistencia.api.persist.query;

import br.com.controlegastos.persistencia.api.persist.tools.MapList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Query {

    private static Logger LOG = LoggerFactory.getLogger(Query.class);

    public static String gerar(Crud crud,Object objetoVariavel) throws Exception {
        StringBuilder sql = new StringBuilder();
        try{
            switch (crud){
                case LISTAR -> {
                    LOG.debug("Iniciando geracao de query Listar a ser executada no banco");
                    return querySelectLista(objetoVariavel);
                }
                case BUSCAR -> {
                    LOG.debug("Iniciando geracao de query Buscar pelo ID a ser executada no banco");
                    return querySelectByID(objetoVariavel);
                }
                case INSERIR -> {
                    LOG.debug("Iniciando geracao de query Insert a ser executada no banco");
                    return queryInsert(objetoVariavel);
                }
                case DELETAR -> {
                    LOG.debug("Iniciando geracao de query Delete pelo Id a ser executada no banco");
                    return queryDeleteByID(objetoVariavel);
                }
                default -> throw new Exception("Crud "+crud+" não identificado");
            }

        }catch (Exception e){
            throw new Exception("Não foi possível criar a query. "+e);
        }
    }

    private static String queryInsert(Object objetoVariavel) throws Exception {

        StringBuilder sqlInsert = new StringBuilder("INSERT INTO ? (?CHAVE?) VALUES (?VALORES?)");

        Map<String,Object> listaCamposValores = MapList.toMap(objetoVariavel);

        String chave = "";
        String valores = "";
        for (Map.Entry<String,Object> linha : listaCamposValores.entrySet()){
            chave += linha.getKey()+ " , ";

            Object valor = linha.getValue();
            if (valor instanceof String || valor instanceof Character) {
                valores += "'"+valor+ "' , ";
            } else {
                valores += valor.toString() +" , ";
            }
        }

        //Serve para remover os últimos " , " da String
        chave = chave.substring(0, chave.length() - 3);
        valores = valores.substring(0, valores.length() - 3);

        //Substituir os ?CHAVE? e ?VALORES? e montar a query
        int indexChave = sqlInsert.indexOf("?CHAVE?");
        int indexValores = sqlInsert.indexOf("?VALORES?");
        sqlInsert.replace(indexChave, indexChave + 7, chave);
        sqlInsert.replace(indexValores, indexValores + 9, valores);

        LOG.debug("Query tipo Insert gerado: "+sqlInsert);
        return sqlInsert.toString();
    }

    private static String querySelectLista(Object objetoVariavel) throws Exception {
        StringBuilder sqlSelect = new StringBuilder("SELECT * obj FROM ?OBJETO?");
        String nomeClasse = objetoVariavel.getClass().getSimpleName();
        int indexChave = sqlSelect.indexOf("?OBJETO?");
        sqlSelect.replace(indexChave, indexChave + 8, nomeClasse);
        LOG.debug("Query tipo Select de listagem gerado: "+sqlSelect);
        return sqlSelect.toString();
    }

    private static String querySelectByID(Object objVariavel){
        StringBuilder sqlSelect = new StringBuilder("SELECT * obj FROM ?OBJETO? WHERE 1=1 AND id = ?");
        String nomeClasse = objVariavel.getClass().getSimpleName();
        int indexChave = sqlSelect.indexOf("?OBJETO?");
        sqlSelect.replace(indexChave, indexChave + 8, nomeClasse);
        LOG.debug("Query tipo Select do Objeto "+nomeClasse+" gerado: "+sqlSelect);
        return sqlSelect.toString();
    }

    private static String queryDeleteByID(Object objVariavel){
        StringBuilder sqlDelete = new StringBuilder("DELETE FROM ?OBJETO? WHERE 1=1 AND id = ?");
        String nomeClasse = objVariavel.getClass().getSimpleName();
        int indexChave = sqlDelete.indexOf("?OBJETO?");
        sqlDelete.replace(indexChave, indexChave + 8, nomeClasse);
        LOG.debug("Query tipo Delete do Objeto "+nomeClasse+" gerado: "+sqlDelete);
        return sqlDelete.toString();
    }

}
