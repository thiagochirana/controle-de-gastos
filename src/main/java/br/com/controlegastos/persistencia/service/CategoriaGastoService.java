package br.com.controlegastos.persistencia.service;

import br.com.controlegastos.entidades.CategoriaGasto;
import br.com.controlegastos.entidades.records.DadosCadastroCategoriaGasto;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroCatGasto;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroModelo;
import br.com.controlegastos.persistencia.database.ConexaoDB;
import br.com.controlegastos.persistencia.database.Executador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaGastoService {


    private static Logger LOG = LoggerFactory.getLogger(CategoriaGastoService.class);

    private static Connection con;

    static {
        try {
            con = ConexaoDB.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CategoriaGastoService() {
    }

    public DadosRespostaCadastroCatGasto cadastrarCategoria(DadosCadastroCategoriaGasto nomeNovaCategoria){
        try{
            LOG.info("Vou buscar cadastrar nova categoria de nome "+nomeNovaCategoria);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Categoria_Gasto (nome,descricao_categoria) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,nomeNovaCategoria.nome());
            ps.setString(2,nomeNovaCategoria.descricao());
            int id = Executador.insertUpdateNoBanco(ps);

            if (id != -1) {
                LOG.info("Categoria foi cadastrada com sucesso!");
                return gerarResposta(id, "Categoria "+nomeNovaCategoria+" foi cadastrada com sucesso!",true);
            } else {
                LOG.info("Categoria não foi cadastrada!");
                return gerarResposta(id, "Categoria "+nomeNovaCategoria+" não foi cadastrada.",false);
            }

        } catch (Exception e){
            LOG.error("Houve erro em cadastrar nova Categoria de gastos de nome "+nomeNovaCategoria,e);
            return gerarResposta(-1,"Houve erro em cadastrar nova Categoria de gastos de nome "+nomeNovaCategoria+". "+e.getMessage(),false);
        }
    }

    public CategoriaGasto obterCategoriaById(long id){
        try{
            LOG.info("Vou obter a categoria Gasto pelo seu id "+id);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Categoria_Gasto WHERE id_categoria = ?");
            ps.setLong(1,id);
            ResultSet rs = Executador.obterResultado(ps);
            if (rs.next()){
                CategoriaGasto cat = new CategoriaGasto(
                       rs.getLong("id_categoria"),
                       rs.getString("nome"),
                       rs.getString("descricao_categoria")
                );
                LOG.info("Encontrado Categoria com o id "+id);
                return cat;
            } else {
                LOG.warn("Categoria de id "+id+" não encontrada");
                return null;
            }
        } catch (Exception e){
            LOG.error("houve um erro ao tentar obter a categoria",e);
            return null;
        }
    }

    private DadosRespostaCadastroCatGasto gerarResposta(long id, String mensagem, boolean cadastrou){
        return new DadosRespostaCadastroCatGasto(
                id,
                mensagem,
                cadastrou
        );
    }

    public List<CategoriaGasto> listarCategoriasGasto() throws Exception {
        try{
            LOG.info("Obtendo lista de categorias de gasto registradas");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Categoria_Gasto");
            ResultSet rs = Executador.obterResultado(ps);
            List<CategoriaGasto> lista = new ArrayList<>();

            while (rs.next()) {
                CategoriaGasto categoriaGasto = new CategoriaGasto();
                categoriaGasto.setIdCategoria(rs.getLong("id_categoria"));
                categoriaGasto.setNome(rs.getString("nome"));
                categoriaGasto.setDescricaoCategoria(rs.getString("descricao_categoria"));
                lista.add(categoriaGasto);
            }

            LOG.info("Lista obtida. Quantidade de categorias registradas: "+lista.size());
            return lista;
        } catch (Exception e){
            LOG.error("Houve um erro ao tentar obter todas as Categorias registradas",e);
            return null;
        }
    }
}
