package br.com.controlegastos.service;

import br.com.controlegastos.entidades.Modelo;
import br.com.controlegastos.entidades.records.DadosCadastroModelo;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroModelo;
import br.com.controlegastos.persistencia.database.ConexaoDB;
import br.com.controlegastos.persistencia.database.Executador;
import br.com.controlegastos.util.Arquivos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloService {

    private static Logger LOG = LoggerFactory.getLogger(ModeloService.class);

    private static Connection con;

    static {
        try {
            con = ConexaoDB.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloService() {
    }

    public DadosRespostaCadastroModelo cadastrarModelo(DadosCadastroModelo dados) throws Exception {
        LOG.info("Irei buscar cadastrar modelo de nome "+dados.nome());
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO Modelo (nome,imagem,marca_id) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,dados.nome());
            ps.setBytes(2, Arquivos.converterCaminhoArquivoParaBytes(dados.caminhoImagem()));
            ps.setLong(3,dados.marcaId());
            LOG.info("Query pronta para execução para persistir Modelo no banco, vou cadastrar.");
            ResultSet rs = ps.executeQuery();
            DadosRespostaCadastroModelo data = new DadosRespostaCadastroModelo(
                rs.getLong("id_modelo"),
                "Modelo de nome "+ dados.nome()+ " foi cadastrado",
                    true
            ) ;
            return data;
        }catch (Exception e){
            LOG.error("Erro ao persistir o Modelo.",e);
            DadosRespostaCadastroModelo data = new DadosRespostaCadastroModelo(
                    0,
                    "Modelo de nome "+ dados.nome()+ " não foi cadastrado. Motivo: "+e.getMessage(),
                    false
            ) ;
            return data;
        }
    }

    public List<Modelo> listaModelos() throws Exception {
        LOG.info("Montarei lista de modelos e enviarei ao cliente.");
        try{
            List<Modelo> lista = new ArrayList<Modelo>();
            String query = "SELECT * FROM Modelo";
            ResultSet rs = Executador.obterResultado(query);
            int rows = rs.getRow();
            if (rows > 0) {
                LOG.info("Encontrado "+rows+" modelos cadastrados.");
            }
            while (rs.next()) {
                Modelo m = new Modelo(
                    rs.getLong("id_modelo"),
                    rs.getString("nome"),
                    rs.getBytes("imagem"),
                    rs.getLong("marca_id")
                );
                lista.add(m);
            }

            return lista;
        }catch (Exception e){
            LOG.error("Erro ao buscar lista de modelos cadastrados.",e);
            throw e;
        }
    }
}
