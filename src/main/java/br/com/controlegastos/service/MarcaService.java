package br.com.controlegastos.service;

import br.com.controlegastos.entidades.Marca;
import br.com.controlegastos.entidades.records.DadosCadastroMarca;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroMarca;
import br.com.controlegastos.persistencia.database.ConexaoDB;
import br.com.controlegastos.persistencia.database.Executador;
import br.com.controlegastos.util.Conversor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaService{

    private static Logger LOG = LoggerFactory.getLogger(MarcaService.class);

    private static Connection con;

    static {
        try {
            con = ConexaoDB.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MarcaService() {
    }

    public DadosRespostaCadastroMarca cadastrarMarca(DadosCadastroMarca dados) throws Exception {
        LOG.info("irei buscar cadastrar Marca -> "+dados);
        try{
            String caminho = dados.caminhoArquivo();
            PreparedStatement stm = con.prepareStatement("INSERT INTO Marca (nome,logotipo_img) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1,dados.nome().toUpperCase().trim());
            if (caminho != null){
                stm.setBytes(2, Conversor.converterCaminhoArquivoParaBytes(caminho));
            } else {
                stm.setBytes(2, null);
            }
            int id = Executador.insertUpdateNoBanco(stm);
            if (id > 0){
                LOG.info("Marca salva com sucesso com o ID "+id);
                return new DadosRespostaCadastroMarca(
                        id,
                        "Marca "+dados.nome()+ " salva com sucesso.",
                        true
                );
            } else {
                throw new Exception("Ocorreu um erro ao cadastrar Marca. ");
            }
        }catch (Exception e){
            LOG.info("Marca não foi cadastrada. ",e);
            return new DadosRespostaCadastroMarca(
                    -1,
                    "Ocorreu um erro ao cadastrar Marca "+dados.nome(),
                    false
            );
        }
    }

    public Marca obterMarcaByNome(String nome) throws Exception {
        String marcaNome = nome.trim().toUpperCase();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT m FROM Marca m WHERE m.nome = ?");
            ps.setString(1, marcaNome);
            ResultSet rs = ps.executeQuery();
            int row = rs.getRow();
            if (row==0){
                LOG.warn("Marca de nome "+ marcaNome + " não existe, logo vou persistir esta nova Marca");
                throw new SQLException("Marca de nome "+ marcaNome + " não existe, logo vou persistir esta nova Marca");
            }
            Marca marca = new Marca(
                    rs.getLong("id_marca"),
                    rs.getString("nome"),
                    rs.getBytes("logotipo_img")
            );
            return marca;
        }catch (SQLException pe){
            DadosRespostaCadastroMarca data = cadastrarMarca(new DadosCadastroMarca(
                    marcaNome,
                    null
            ));
            Marca marca = obterMarcaById(data.idMarca());
            return marca;
        }
        catch (Exception e){
            LOG.error("Não foi possível obter marca pelo nome "+marcaNome,e);
            throw e;
        }
    }

    public Marca obterMarcaById(long id) throws Exception{
        try {
            PreparedStatement ps = con.prepareStatement("SELECT m FROM Marca m WHERE m.id_marca = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            int row = rs.getRow();
            if (row != 1){
                throw new Exception("Marca de ID " + id + " não foi encontrada");
            }
            Marca marca = new Marca(
                    rs.getLong("id_marca"),
                    rs.getString("nome"),
                    rs.getBytes("logotipo_img")
            );
            return marca;
        }catch (Exception e){
            LOG.error("Marca de ID " + id + " não foi encontrada",e);
            throw e;
        }
    }

    public List<Marca> listarMarcas() throws Exception {
        try {
            List<Marca> lista = new ArrayList<>();
            String query = "SELECT * FROM Marca";
            ResultSet rs = Executador.obterResultado(query);
            while (rs.next()) {
                Marca m = new Marca(
                        rs.getLong("id_marca"),
                        rs.getString("nome"),
                        rs.getBytes("logotipo_img")
                );
                lista.add(m);
            }
            LOG.info("Lista de marcas obtida. Tamanho da lista :"+lista.size());
            return lista;
        } catch (Exception e){
            LOG.error("Houve um erro ao tentar montar a lista de de Marcas.", e);
            throw e;
        }
    }
}
