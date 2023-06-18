package br.com.controlegastos.persistencia.service;

import br.com.controlegastos.entidades.CategoriaGasto;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroCatGasto;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroModelo;
import br.com.controlegastos.persistencia.database.ConexaoDB;
import br.com.controlegastos.persistencia.database.Executador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public DadosRespostaCadastroCatGasto cadastrarCategoria(String nomeNovaCategoria){
        try{
            LOG.info("Vou buscar cadastrar nova categoria de nome "+nomeNovaCategoria);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Categoria_Gasto (nome) VALUES (?)");
            ps.setString(1,nomeNovaCategoria);
            int id = Executador.insertUpdateNoBanco(ps);

            if (id != -1) {
                LOG.info("Categoria foi cadastrada com sucesso!");
                return gerarResposta(id, "Categoria "+nomeNovaCategoria+" foi cadastrada com sucesso!",true);
            } else {
                return gerarResposta(id, "Categoria "+nomeNovaCategoria+" não foi cadastrada.",false);
            }

        } catch (Exception e){
            LOG.error("Houve erro em cadastrar nova Categoria de gastos de nome "+nomeNovaCategoria,e);
            return gerarResposta(-1,"Houve erro em cadastrar nova Categoria de gastos de nome "+nomeNovaCategoria+". "+e.getMessage(),false);
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
            String query = "SELECT * FROM Categoria_Gasto";
            ResultSet rs = Executador.obterResultado(query);
            List<CategoriaGasto> lista = new ArrayList<>();

            while (rs.next()) {
                CategoriaGasto categoriaGasto = new CategoriaGasto();
                categoriaGasto.setIdCategoria(rs.getLong("id"));
                categoriaGasto.setNome(rs.getString("nome"));
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
