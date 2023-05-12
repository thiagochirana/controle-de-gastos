package br.com.controlegastos.persistencia.propriedades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Config {

    static Propriedade propriedade;

    public static String obterPropriedade(String chaveConfiguracao) throws PropriedadeException, IOException {
        try{
            String caminhoArquivo = "C:\\ControleGastos\\Configuracao\\configControleGasto.properties";
            String valorEncontrado = null;
            for(Map.Entry<String, String> linha : loadProperties(caminhoArquivo).entrySet()){
                if(linha.getKey().toUpperCase().equals(chaveConfiguracao.toUpperCase())){
                    valorEncontrado = linha.getValue();
                }
            }
            if (valorEncontrado == null){
                throw new PropriedadeException("Propriedade "+chaveConfiguracao.toUpperCase()+" não encontrada");
            }
            return valorEncontrado;
        } catch (PropriedadeException pe){
            throw pe;
        } catch (IOException e) {
            throw e;
        }
    }

    private static Map<String, String> loadProperties(String caminhoArquivo) throws IOException {
        if (propriedade.getListagem().size() == 0) {
            try(FileInputStream arquivo = new FileInputStream(caminhoArquivo)){
                Properties props = new Properties();
                props.load(arquivo);
                for (Map.Entry<Object, Object> linha : props.entrySet()){
                    propriedade.put(linha.getKey().toString(), linha.getValue().toString());
                }
                return propriedade.getListagem();
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("Arquivo de configuracao nao encontrado no caminho "+caminhoArquivo);
            } catch (IOException ei) {
                throw ei;
            }
        } else {
            return propriedade.getListagem();
        }
    }
}
