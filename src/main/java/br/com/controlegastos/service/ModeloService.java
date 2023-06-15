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
            String caminhoImg = null;
            PreparedStatement ps = con.prepareStatement("INSERT INTO Modelo (nome,imagem,marca_id) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,dados.nome());

            if (dados.caminhoImagem() != null) {
                ps.setBytes(2, Arquivos.converterCaminhoArquivoParaBytes(dados.caminhoImagem()));
            } else {
                ps.setBytes(2, null);
            }

            ps.setLong(3,dados.marcaId());

            LOG.info("Query pronta para execução para persistir Modelo no banco, vou cadastrar.");

            int idGerado = Executador.insertUpdateNoBanco(ps);

            Modelo modelo = obterModeloById(Long.valueOf(idGerado));

            DadosRespostaCadastroModelo data;
            if (modelo == null){
                data = new DadosRespostaCadastroModelo(
                        -1,
                        "Modelo de nome "+ dados.nome()+ " não foi cadastrado. Motivo: Não foi possível cadastrar o modelo.",
                        false
                );
            }


            data = new DadosRespostaCadastroModelo(
                modelo.getIdModelo(),
                "Modelo de nome "+ modelo.getNome()+ " foi cadastrado",
                    true
            );
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

    public Modelo obterModeloById(Long id) throws Exception {
        try{
            LOG.info("Buscando modelo pelo id "+id+" ...");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Modelo WHERE id_modelo= ?");
            ps.setLong(1, id);
            ResultSet rs = Executador.obterResultado(ps);
            if(rs.next()) {
                Modelo mod = new Modelo(
                        id,
                        rs.getString("nome"),
                        rs.getBytes("imagem"),
                        rs.getLong("marca_id")
                );
                LOG.info("Modelo obtido, vou retornar ao cliente solicitante.");
                return mod;
            } else {
                LOG.info("Modelo não encontrado. Vou retornar consulta Nula.");
                return null;
            }
        }catch (Exception e) {
            LOG.error("Erro ao buscar modelo pelo id "+id+".",e);
            throw e;
        }
    }

    public boolean verificarSeModeloCadastradoParaEstaMarca(String nomeModelo, long idMarca){
        LOG.info("Verificando se modelo existe cadastrado com esta marca.");
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Modelo WHERE nome=? AND marca_id=?");
            ps.setString(1, nomeModelo.toUpperCase().trim());
            ps.setLong(2, idMarca);
            ResultSet rs = Executador.obterResultado(ps);
            if (rs.next()) {
                LOG.info("Modelo existe cadastrado com esta marca.");
                return true;
            } else {
                LOG.info("Modelo não existe cadastrado com esta marca.");
                return false;
            }
        } catch (Exception e) {
            LOG.error("Erro ao verificar se modelo existe cadastrado com esta marca.",e);
        }
        return false;
    }

    public Modelo obterModelo(String nomeModelo, long idMarca) throws Exception{
        LOG.info("Vou buscar obter modelo de nome "+nomeModelo);
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Modelo WHERE nome=? AND marca_id=?");
            ps.setString(1, nomeModelo.toUpperCase().trim());
            ps.setLong(2, idMarca);
            ResultSet rs = Executador.obterResultado(ps);
            Modelo modelo;
            if (rs.next()) {
                LOG.info("Modelo existe cadastrado com esta marca. Vou enviar ao cliente");
                modelo = new Modelo(
                        rs.getLong("id_modelo"),
                        rs.getString("nome"),
                        rs.getBytes("imagem"),
                        rs.getLong("marca_id")
                );
            } else {
                LOG.info("Modelo não existe cadastrado com esta marca. Retornarei nulo");
                return null;
            }
            return modelo;
        } catch ( Exception e){
            LOG.error("Houve um erro ao tentar obter Modelo de nome "+nomeModelo,e);
            throw e;
        }
    }
}
