package br.com.controlegastos.controle;

import br.com.controlegastos.entidades.CategoriaGasto;
import br.com.controlegastos.entidades.records.DadosCadastroCategoriaGasto;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroCatGasto;
import br.com.controlegastos.persistencia.database.ConexaoDB;
import br.com.controlegastos.persistencia.service.CategoriaGastoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class CategoriaGastoController {

    private static Logger LOG = LoggerFactory.getLogger(CategoriaGastoController.class);

    private static Connection con;

    private CategoriaGastoService catGasto;

    static {
        try {
            con = ConexaoDB.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CategoriaGastoController() {
        this.catGasto = new CategoriaGastoService();
    }

    public DadosRespostaCadastroCatGasto cadastrarCategoria(DadosCadastroCategoriaGasto dados){
        try{
            String nome = "";
            //verifica se nome está normal
            if (dados.nome() == null || dados.nome().equals(" ")){
                LOG.warn("Cadastro de nome de nova categoria está incorreto. Nome está vazio.");
                return new DadosRespostaCadastroCatGasto(
                        0,
                        "Cadastro de nome de nova categoria está incorreto. Nome está vazio.",
                        false
                );
            } else {
                nome = dados.nome().trim().toUpperCase();
            }

            String desc = "";
            if (dados.descricao() == null){
                LOG.info("Descricao da categoria está nulo, mas isso não impacta no cadastro, continuarei o cadastro.");
            } else {
                desc = dados.descricao().trim();
            }

            return catGasto.cadastrarCategoria(new DadosCadastroCategoriaGasto(nome, desc));
        }catch ( Exception e){
            LOG.error("Houve um erro ao tentar cadastrar nova categoria",e);
            return new DadosRespostaCadastroCatGasto(
                    0,
                    "Houve um erro ao tentar cadastrar nova categoria: "+e.getMessage(),
                    false
            );
        }
    }

    public List<CategoriaGasto> listarCategoria() throws Exception{
        return catGasto.listarCategoriasGasto();
    }
}
