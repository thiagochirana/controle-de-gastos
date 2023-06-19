package br.com.controlegastos.persistencia.service;

import br.com.controlegastos.entidades.CategoriaGasto;
import br.com.controlegastos.entidades.Gastos;
import br.com.controlegastos.entidades.Modelo;
import br.com.controlegastos.entidades.Veiculo;
import br.com.controlegastos.entidades.records.DadosGastos;
import br.com.controlegastos.entidades.records.DadosListaGastoCategoria;
import br.com.controlegastos.entidades.records.DadosRegistroDeGasto;
import br.com.controlegastos.entidades.records.DadosRespostaRegistroGasto;
import br.com.controlegastos.persistencia.database.ConexaoDB;
import br.com.controlegastos.persistencia.database.Executador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class GastosService {

    private static Logger LOG = LoggerFactory.getLogger(GastosService.class);

    private static Connection con;
    
    private CategoriaGastoService catGastosService;

    private ModeloService modelo;

    private VeiculoService veiculoS;

    static {
        try {
            con = ConexaoDB.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GastosService() {
        this.catGastosService = new CategoriaGastoService();
        this.modelo = new ModeloService();
        this.veiculoS = new VeiculoService();
    }

    public DadosRespostaRegistroGasto registrarGasto(DadosRegistroDeGasto dados) throws Exception{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate data = LocalDate.parse(dados.data(), formatter);

            LOG.info("Registro de gasto iniciado. Buscarei registrar este dado: "+dados);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Gastos (descricao, data, valor, veiculo_id, categoria_id)" +
                    "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dados.descricao());
            ps.setDate(2,java.sql.Date.valueOf(data));
            ps.setDouble(3, dados.valor());
            ps.setLong(4, dados.veiculoId());
            ps.setLong(5, dados.categoriaId());

            int id = Executador.insertUpdateNoBanco(ps);
            if (id != 1) {
                LOG.info("Gasto registrado com sucesso");
                return new DadosRespostaRegistroGasto(id,"Gasto registrado com sucesso",dados.valor(),true);
            } else {
                LOG.error("Gasto não foi registrado");
                return new DadosRespostaRegistroGasto(id,"Gasto não foi registrado. Tente novamente. Se erro persistir, contate o suporte",dados.valor(),false);
            }
        } catch (Exception e){
            LOG.error("Houve um erro ao tentar registrar o gasto",e);
            return new DadosRespostaRegistroGasto(
                    0,
                    "Gasto não foi registrado, "+e.getMessage(),
                    0,
                    false
            );
        }
    }

    public DadosRespostaRegistroGasto editarRegistro(long id, DadosRegistroDeGasto dados) throws Exception{
        try{
            int contadorDeParametros = 0;

            int posDescricao = 0;
            int posData = 0;
            int posValor = 0;
            int posVeiculo = 0;
            int posCategoria = 0;

            LOG.info("Edição ao registro de id "+id+" solicitada. Vou buscar realizar a alteracão.");
            StringBuilder sql = new StringBuilder("UPDATE Gastos SET ");

            if (dados.descricao() != null){
                sql.append("descricao = ?");

                contadorDeParametros++;
                posDescricao = contadorDeParametros;
            }

            if (dados.data() != null){
                if (verificaSeTemParametroAntes(sql)){
                    sql.append(", data = ?");
                } else {
                    sql.append(" data = ?");
                }

                contadorDeParametros++;
                posData = contadorDeParametros;
            }

            if (dados.valor() >= 0){
                if (verificaSeTemParametroAntes(sql)){
                    sql.append(", valor = ?");
                } else {
                    sql.append(" valor = ?");
                }

                contadorDeParametros++;
                posValor = contadorDeParametros;
            }

            if (dados.veiculoId() > 0){
                if (verificaSeTemParametroAntes(sql)){
                    sql.append(", veiculo_id = ?");
                } else {
                    sql.append(" veiculo_id = ?");
                }

                contadorDeParametros++;
                posVeiculo = contadorDeParametros;
            }

            if (dados.categoriaId() > 0){
                if (verificaSeTemParametroAntes(sql)){
                    sql.append(", categoria_id = ?");
                } else {
                    sql.append(" categoria_id = ?");
                }

                contadorDeParametros++;
                posCategoria = contadorDeParametros;
            }

            contadorDeParametros++;
            sql.append("WHERE id_gastos = ?");

            PreparedStatement ps = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            if (posDescricao > 0){
                ps.setString(posDescricao, dados.descricao());
            }
            if (posData > 0){
                ps.setDate(posData,java.sql.Date.valueOf(dados.data()));
            }
            if (posValor > 0){
                ps.setDouble(posValor, dados.valor());
            }
            if (posVeiculo > 0){
                ps.setLong(posVeiculo, dados.veiculoId());
            }
            if (posCategoria > 0){
                ps.setLong(posCategoria, dados.categoriaId());
            }

            ps.setLong(contadorDeParametros, id);

            int idAlterado = Executador.insertUpdateNoBanco(ps);
            if (id!= 1) {
                LOG.info("Alteracao realizada com sucesso!");
                return new DadosRespostaRegistroGasto(idAlterado,"Alteracao realizada com sucesso",dados.valor(),true);
            } else {
                LOG.error("Alteracao não foi realizada");
                return new DadosRespostaRegistroGasto(idAlterado, "Alteracao não foi realizada. Tente novamente. Se erro persistir, contate o suporte", dados.valor(), false);
            }
        } catch (Exception e){
            LOG.error("Houve um erro ao tentar alterar um gasto já registrado");
            return new DadosRespostaRegistroGasto(
                    0,
                    "Alteracao não foi realizada, "+e.getMessage(),
                    0,
                    false
            );
        }
    }

    public DadosRespostaRegistroGasto deletarGastoById(long id){
        try{
            LOG.info("Buscarei deletar registro de gasto de id"+id);

            PreparedStatement ps = con.prepareStatement("DELETE FROM Gastos WHERE id_gastos = ?");
            ps.setLong(1,id);

            ps.execute();

            LOG.info("Gasto de id "+id+" foi deletado com sucesso");

            return new DadosRespostaRegistroGasto(1,"Gasto foi deletado com sucesso",0,true);

        } catch (Exception e){
            LOG.error("Houve um erro ao tentar deletar registro de gasto.",e);
            return new DadosRespostaRegistroGasto(
                    id,
                    "Registro de gasto não foi deletado. "+e.getMessage(),
                    0,
                    false
            );
        }
    }

    public List<DadosListaGastoCategoria> listaGastosCategoriaPorData(String dataInicial, String dataFinal){
        try{
            List<DadosListaGastoCategoria> listaGastos = new ArrayList<>();
            LOG.info("Vou listar os gastos totais de cada Categoria");
            PreparedStatement psGastos = con.prepareStatement("SELECT * FROM Gastos WHERE data BETWEEN ? AND ?");
            psGastos.setDate(1, java.sql.Date.valueOf(dataInicial));
            psGastos.setDate(2, java.sql.Date.valueOf(dataFinal));
            List<Gastos> listaAux = getGastosListados(psGastos);

            List<CategoriaGasto> listaCategoria = catGastosService.listarCategoriasGasto();

            for (CategoriaGasto cat : listaCategoria){
                double valores = 0.0;

                for (Gastos ga : listaAux){
                    if (ga.getCategoriaId() == cat.getIdCategoria()){
                        valores += ga.getValor();
                    }
                }

                listaGastos.add(new DadosListaGastoCategoria(
                        cat.getIdCategoria(),
                        cat.getNome(),
                        cat.getDescricaoCategoria(),
                        dataInicial,
                        dataFinal,
                        valores
                ));
            }

            LOG.info("Obtive um total de "+listaGastos.size()+" resultados. Vou retornar o relatório ao cliente.");

            return listaGastos;
        } catch ( Exception e){
            LOG.error("Houve um erro ao tentar listar os gastos de cada categoria.",e);
            return null;
        }
    }

    public List<DadosGastos> listarGastosDeVeiculoEspecifico(Veiculo veiculo, String dataInicial, String dataFinal){
        try{
            Date dataIni = Date.valueOf(dataInicial);
            Date dataFim = Date.valueOf(dataFinal);

            LOG.info("Irei gerar lista de gastos de veículo "+veiculo.getDescricaoVeiculo());

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Gastos WHERE veiculo_id = ? AND data BETWEEN ? AND ?");
            ps.setLong(1, veiculo.getIdVeiculo());
            ps.setDate(2, dataIni.valueOf(dataInicial));
            ps.setDate(3, dataFim.valueOf(dataFinal));
            List<Gastos> lista = getGastosListados(ps);
            LOG.info("Lista filtrada e obtida para datas. Tamanho da lista: "+lista.size());
            List<DadosGastos> dg = new ArrayList<>();

            for(Gastos g : lista){
                Veiculo v = veiculoS.obterDadosVeiculoById(g.getVeiculoId());
                Modelo m = modelo.obterModeloById(v.getModeloId());
                CategoriaGasto cg = catGastosService.obterCategoriaById(g.getCategoriaId());
                DadosGastos d = new DadosGastos(
                    v.getPlaca()+" - "+m.getNome(),
                        g.getValor(),
                        cg.getNome(),
                        g.getData().toString()
                );

                dg.add(d);
            }

            return dg;
        } catch (Exception e){
            LOG.error("Houve um erro ao tentar obter lista.",e);
            return null;
        }
    }

    public List<Gastos> listarTodosGastosByDataInicialFinal(boolean ativo, String dataInicial, String dataFinal) {
        try {
            LOG.info("Vou obter lista de Gastos do veículos "+(ativo ? "ativos" : "inativos" )+" de data "+dataInicial+" até "+dataFinal);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Gastos g " +
                    "JOIN Veiculo v ON g.veiculo_id = v.id_veiculo " +
                    "WHERE v.ativo= ? AND g.data BETWEEN ? AND ?");
            ps.setBoolean(1, ativo);
            ps.setDate(2, java.sql.Date.valueOf(dataInicial));
            ps.setDate(3, java.sql.Date.valueOf(dataFinal));

            List<Gastos> lista = getGastosListados(ps);
            LOG.info("Lista filtrada e obtida para datas. Tamanho da lista: "+lista.size());
            return lista;
        } catch ( Exception e){
            LOG.error("Houve um erro ao tentar obter lista.",e);
            return null;
        }
    }

    private List<Gastos> getGastosListados(PreparedStatement ps) throws Exception {
        List<Gastos> lista = new ArrayList<>();
        ResultSet rs = Executador.obterResultado(ps);
        while (rs.next()){
            Gastos ga = new Gastos(
                    rs.getLong("id_gastos"),
                    rs.getString("descricao"),
                    rs.getDate("data"),
                    rs.getDouble("valor"),
                    rs.getLong("veiculo_id"),
                    rs.getLong("categoria_id")
            );
            lista.add(ga);
        }
        return lista;
    }

    private boolean verificaSeTemParametroAntes(StringBuilder sql){
        char ultChar = sql.charAt(sql.length()-1);
        if (ultChar == '?'){
            return true;
        } else {
            return false;
        }
    }
}
