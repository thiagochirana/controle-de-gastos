package br.com.controlegastos.persistencia.propriedades;

import br.com.controlegastos.ControleGastosStartAplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propriedade {

    private static Properties properties = new Properties();

    public static String getValor(String chave){
        try (InputStream input = ControleGastosStartAplication.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Arquivo config.properties não encontrado.");
                throw new Exception("Arquivo config.properties não encontrado.");
            }
            properties.load(input);
            return properties.getProperty(chave);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
