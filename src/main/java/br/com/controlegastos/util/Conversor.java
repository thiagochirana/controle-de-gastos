package br.com.controlegastos.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class Conversor {

    private static Logger LOG = LoggerFactory.getLogger(Conversor.class);

    public static byte[] converterCaminhoArquivoParaBytes(String filePath) throws IOException {
        FileInputStream fileInputStream = null;
        byte[] fileData = null;

        LOG.info("Converter o arquivo de caminho "+filePath+ " para bytes");
        try {
            fileInputStream = new FileInputStream(filePath);
            fileData = new byte[fileInputStream.available()];
            fileInputStream.read(fileData);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }

        LOG.info("Arquivo "+filePath+" Convertido com sucesso.");
        return fileData;
    }
}
