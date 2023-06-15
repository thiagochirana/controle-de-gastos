package br.com.controlegastos.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Arquivos {

    private static Logger LOG = LoggerFactory.getLogger(Arquivos.class);

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

    public static boolean verficaSeArquivoExiste(String caminhoArquivo) throws Exception {
        try{
            LOG.info("Irei verificar se existe arquivo no caminho "+caminhoArquivo);
            File file = new File(caminhoArquivo);
            boolean existe;
            if (existe = file.exists()) {
                LOG.info("Arquivo de caminho "+caminhoArquivo+" existe.");
            } else {
                LOG.info("Arquivo de caminho "+caminhoArquivo+" não existe.");
            }
            return existe;
        }catch (Exception e) {
            LOG.error("Não foi possível obter informações do arquivo no suposto caminho "+caminhoArquivo,e);
            throw new Exception("Não foi possível obter informações do arquivo no suposto caminho "+caminhoArquivo);
        }
    }

    public static boolean isImagem(String caminhoArquivo) throws Exception{
        try {
            LOG.info("Irei verificar se arquivo no caminho "+caminhoArquivo+ " é uma imagem válida. (JPG, PNG, JPEG ou JFIF)");

            Path path = Paths.get(caminhoArquivo);
            String extensao = obterExtensaoArquivo(path);

            if (extensao != null) {
                // Verificar se a extensão corresponde a um formato de imagem conhecido
                boolean correto = extensao.equalsIgnoreCase("jpg")
                        || extensao.equalsIgnoreCase("jpeg")
                        || extensao.equalsIgnoreCase("png")
                        || extensao.equalsIgnoreCase("jfif");

                if (correto) {
                    LOG.info("Arquivo é uma imagem valida.");
                    return true;
                } else {
                    LOG.warn("Arquivo não é válido como imagem. Por favor escolha um arquivo de tipo JPG, PNG, JPEG ou JFIF");
                }
            }
            return false;
        }catch (Exception e){
            LOG.error("Erro ao verificar se arquivo é valido ou não",e);
            throw new Exception("Erro ao verificar se arquivo é valido ou não",e);
        }
    }

    private static String obterExtensaoArquivo(Path path) {
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            String ext = fileName.substring(dotIndex + 1).toLowerCase();
            LOG.info("Extensão do arquivo identificado : "+ext);
            return ext;
        }

        return null;
    }
}
