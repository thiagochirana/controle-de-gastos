package br.com.controlegastos.persistencia.service;

import br.com.controlegastos.entidades.CategoriaVeiculo;
import br.com.controlegastos.entidades.Marca;
import br.com.controlegastos.entidades.Modelo;
import br.com.controlegastos.entidades.Veiculo;
import br.com.controlegastos.entidades.enums.TipoCombustivel;
import br.com.controlegastos.entidades.records.DadosCadastroVeiculo;
import br.com.controlegastos.entidades.records.DadosRespostaCadastroCategoriaVeiculo;
import br.com.controlegastos.entidades.records.DadosRespostaVeiculo;
import br.com.controlegastos.entidades.records.DadosVeiculo;
import br.com.controlegastos.persistencia.database.ConexaoDB;
import br.com.controlegastos.persistencia.database.Executador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoService {

    private static Logger LOG = LoggerFactory.getLogger(VeiculoService.class);

    MarcaService marca = new MarcaService();
    ModeloService modelo = new ModeloService();

    private static Connection con;

    static {
        try {
            con = ConexaoDB.conectar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public VeiculoService() {}

    public Veiculo obterDadosVeiculoById(long id) throws Exception{
        try{
            LOG.info("Vou buscar o veículo de ID "+id);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Veiculo WHERE id_veiculo =?");
            ps.setLong(1, id);

            ResultSet rs = Executador.obterResultado(ps);

            if(rs.next()){
                Veiculo ve = new Veiculo(
                        rs.getLong("id_veiculo"),
                        rs.getBoolean("tem_placa"),
                        rs.getString("placa"),
                        rs.getString("tipo_combustivel"),
                        rs.getFloat("quilometragem"),
                        rs.getString("categoria_veiculo"),
                        rs.getLong("modelo_id"),
                        rs.getLong("proprietario_id"),
                        rs.getBoolean("ativo")
                );
                LOG.info("Resultado obtido: "+ve+". Vou retornar o cliente.");
                return ve;
            } else {
                LOG.warn("Nenhum veículo encontrado com o id "+id);
                throw new Exception("Nenhum veículo encontrado com o id "+id);
            }
        } catch (Exception e){
            LOG.error("Houve um erro ao tentar realizar a consulta ao veículo de id "+id,e);
            throw e;
        }
    }


    public DadosRespostaVeiculo cadastrarVeiculo(DadosCadastroVeiculo dados) throws Exception{
        String placa = dados.placa();
        LOG.info("Irei buscar cadastrar Veículo de placa");
        try{
            placa = placa.trim().toUpperCase();

            PreparedStatement ps = con.prepareStatement("INSERT INTO Veiculo" +
                    "(tem_placa,placa,tipo_combustivel,quilometragem,categoria_veiculo,ativo,modelo_id,proprietario_id)" +
                    "VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setBoolean(1, dados.temPlaca());
            ps.setString(2, dados.temPlaca() ? placa : "SEM PLACA");
            ps.setString(3, dados.tipoCombustivel());
            ps.setFloat(4, dados.quilometragem());
            ps.setString(5, dados.categoriaVeiculo());
            ps.setBoolean(6, true); //Por padrão qualquer veículo cadastrado significa que é novo
            ps.setLong(7, dados.modeloId());
            ps.setLong(8, dados.proprietarioId());

            long id = Executador.insertUpdateNoBanco(ps);

            LOG.info("Cadastro de veículo realizado com sucesso com o id "+id+"! Vou retornar a informacao ao Cliente.");

            DadosRespostaVeiculo resposta = new DadosRespostaVeiculo(
                    id,
                    "Veículo cadastrado com sucesso!",
                    true
            );
            return resposta;
        }catch (Exception e){
            LOG.error("Houve um erro ao tentar cadastrar o veículo");
            return new DadosRespostaVeiculo(
                    -1,
                    "Erro ao tentar cadastrar o veículo",
                    false
            );
        }
    }

    public DadosRespostaVeiculo ativaDesativaVeiculo(long id, boolean atividade) throws Exception{
        try{
            LOG.info("Irei "+ (atividade ? "ativar": "desativar") +" veiculo de id "+id);

            PreparedStatement ps = con.prepareStatement("UPDATE Veiculo SET ativo = ? WHERE id_veiculo = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, atividade);
            ps.setLong(2, id);
            int idObtido = Executador.insertUpdateNoBanco(ps);
            if (idObtido == id) {
                LOG.info("Veículo de id "+id+" "+ (atividade ? "ativado": "desativado") +" com sucesso!");
                return new DadosRespostaVeiculo(
                        id,
                        "Veículo de id "+id+" "+ (atividade ? "ativado": "desativado") +" com sucesso!",
                        true);
            } else {
                throw new Exception("Veículo de id "+id+" não foi "+ (atividade ? "ativado": "desativado"));
            }
        } catch (Exception e){
            LOG.info("Houve um erro ao tentar "+ (atividade ? "ativar": "desativar") +" veículo de id "+id, e);
            return new DadosRespostaVeiculo(
                    id,
                    "Houve um erro ao tentar "+ (atividade ? "ativar": "desativar") +" veículo de id "+id+". "+ e.getMessage(),
                    false);
        }
    }

    //Persistencia das Categorias

    public DadosRespostaCadastroCategoriaVeiculo cadastrarCategoriaVeiculo(String categoria){
        try{
            String cate = categoria.trim().toUpperCase();
            LOG.info("Irei cadastrar uma nova categoria de veiculos");
            PreparedStatement ps = con.prepareStatement("INSERT INTO Categoria_Veiculo (nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,cate);

            long id = Executador.insertUpdateNoBanco(ps);
            if (id != -1){
                LOG.info("Cadastro de categoria de veículo de nome "+cate+" realizado com sucesso");
                return new DadosRespostaCadastroCategoriaVeiculo(
                        id,
                        cate,
                        "Cadastro de categoria de veículo "+cate+" realizado com sucesso",
                        true
                );
            } else {
                LOG.warn("Cadastro de categoria de veículo de nome "+cate+" não aconteceu.");
                return new DadosRespostaCadastroCategoriaVeiculo(
                        id,
                        cate,
                        "Cadastro de categoria de veículo "+cate+" não foi realizada com sucesso",
                        false
                );
            }
        } catch (Exception e){
            LOG.error("Houve um erro ao tentar cadastrar categoria de veículo "+categoria,e);
            return new DadosRespostaCadastroCategoriaVeiculo(
                    0,
                    categoria,
                    "Cadastro de categoria de veículo "+categoria+" não foi realizada. "+e.getMessage(),
                    false
            );
        }
    }

    public boolean verificarSeCategoriaExisteCadastrada(String categoria) throws Exception{
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Categoria_Veiculo WHERE nome = ?");
            ps.setString(1,categoria.trim().toUpperCase());
            ResultSet rs = Executador.obterResultado(ps);
            if (rs.next()){
                LOG.info("Consulta para verificar se tem categoria já cadastrada com mesmo nome obteve um resultado");
                String cat = rs.getString("nome");
                if (cat.equalsIgnoreCase(categoria)){
                    LOG.info("Categoria de nome "+categoria+" já cadastrada. Vou retornar true para o cliente");
                    return true;
                } else {
                    LOG.info("Categoria de nome "+categoria+" não foi cadastrada localmente pois resultado obtivo não é igual ao cadastrado no banco local. Categoria de nome "+categoria+" pode ser cadastrada.");
                    return false;
                }
            } else {
                LOG.info("Categoria de nome "+categoria+" não foi cadastrada localmente. Pode ser cadastrada.");
                return false;
            }
        } catch (Exception e){
            LOG.error("Houve um erro ao tentar obter informação de que se a categoria já foi cadastrada anteriormente",e);
            throw e;
        }
    }

    public List<CategoriaVeiculo> listarCategoriaVeiculo(){
        try{
            LOG.info("Irei listar todas as categorias de veículo e devolver ao cliente");
            List<CategoriaVeiculo> lista = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Categoria_Veiculo");
            ResultSet rs = Executador.obterResultado(ps);
            while (rs.next()){
                lista.add(new CategoriaVeiculo(
                        rs.getLong("id_categoria"),
                        rs.getString("nome")
                ));
            }
            LOG.info("Listagem obtida, tamanho da lista: "+lista.size());
            return lista;
        } catch (Exception e){
            LOG.error("Houve um erro ao listar todas as categorias de veiculo",e);
            return null;
        }
    }

    public CategoriaVeiculo obterCategoriaVeiculoById(long id){
        try{
            LOG.info("Vou buscar Categoria Veiculo de id "+id);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Categoria_Veiculo WHERE id_categoria = ?");
            ps.setLong(1,id);
            ResultSet rs = Executador.obterResultado(ps);
            if (rs.next()){
                CategoriaVeiculo cat = new CategoriaVeiculo(
                        rs.getLong("id_categoria"),
                        rs.getString("nome")
                );
                LOG.info("Categoria de id "+id+" obtida: "+cat);
                return cat;
            } else {
                LOG.warn("Categoria não encotrada, vou retornar null");
                return null;
            }
        } catch (Exception e){
            LOG.error("Houve erro em obter Categoria de Veículo pelo id "+id,e);
            return null;
        }
    }

    //Listar veículo de acordo com a atividade do mesmo
    public List<DadosVeiculo> listarVeiculos(boolean atividade) throws Exception{
        try{
            List<Veiculo> lista = new ArrayList<>();
            LOG.info("Irei listar todos os veículos "+ (atividade ? "ativos": "desativados") +" cadastrados");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Veiculo WHERE ativo = ? ", Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1,atividade);

            return getDadosVeiculos(lista, ps);
        } catch ( Exception e){
            LOG.error("Houve um erro ao listar veículos "+ (atividade ? "ativos": "desativados"),e);
            throw e;
        }
    }



    //Listar todos os veículos já cadastrados
    public List<DadosVeiculo> listarVeiculos() throws Exception{
        try{
            List<Veiculo> lista = new ArrayList<>();
            LOG.info("Irei listar todos os veículos cadastrados");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Veiculo", Statement.RETURN_GENERATED_KEYS);
            return getDadosVeiculos(lista, ps);
        } catch ( Exception e){
            LOG.error("Houve um erro ao listar todos os veículos ",e);
            throw e;
        }
    }

    public List<String> listarTiposCombustivel(){
        List<String> lista = new ArrayList<>();
        for (TipoCombustivel tipo : TipoCombustivel.values()){
            lista.add(tipo.toString().replace("_"," "));
        }
        return lista;
    }


    //Utils da classe
    public boolean verificaCadastroPlaca(String placa) throws Exception{
        placa = placa.trim().toUpperCase();
        try{
            LOG.info("Verificarei se placa já existe cadastrada");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Veiculo WHERE placa = ? ", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, placa);
            boolean result = Executador.obterResultado(ps).next();
            if (result) {
                LOG.info("Veículo de placa "+placa+" já está cadastrada");
                return true;
            } else {
                LOG.info("Veículo de placa "+placa+" não encontrado");
                return false;
            }
        }catch (Exception e){
            LOG.info("Houve um erro ao tentar buscar Veículo pela placa "+placa,e);
            throw new Exception("Erro ao tentar buscar veículo de placa "+placa);
        }
    }

    public boolean verificaSeVeiculoIdExiste(long id) throws Exception{
        try {
            LOG.info("Irei verificar se existe veículo cadastrado com id "+id);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Veiculo WHERE id_veiculo = ?",Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,id);
            boolean result = Executador.obterResultado(ps).next();
            if (result) {
                LOG.info("Veículo de id "+id+" existe salvo no banco local");
                return true;
            } else {
                LOG.info("Veículo de id "+id+" não existe salvo localmente");
                return false;
            }
        } catch (Exception e){
            LOG.error("Houve um erro ao tentar consultar veículo com id "+id,e);
            throw e;
        }
    }


    private List<DadosVeiculo> getDadosVeiculos(List<Veiculo> lista, PreparedStatement ps) throws Exception {
        List<DadosVeiculo> listagem = new ArrayList<>();
        for (Veiculo v : getVeiculos(lista, ps)){
            Modelo m = modelo.obterModeloById(v.getModeloId());
            CategoriaVeiculo cat = obterCategoriaVeiculoById(Long.parseLong(v.getCategoriaVeiculo()));
            Marca ma = marca.obterMarcaById(m.getMarcaId());
            DadosVeiculo data = new DadosVeiculo(
                    v.getIdVeiculo(),
                    v.getPlaca(),
                    m.getNome(),
                    ma.getNome(),
                    cat.getNome(),
                    v.getQuilometragem(),
                    v.getTipoCombustivel()
            );
            listagem.add(data);
        }
        LOG.info("Veículos cadastrados: "+listagem.size());
        return listagem;
    }

    private List<Veiculo> getVeiculos(List<Veiculo> lista, PreparedStatement ps) throws SQLException {
        ResultSet rs = Executador.obterResultado(ps);
        while (rs.next()) {
            lista.add(new Veiculo(
                    rs.getLong("id_veiculo"),
                    rs.getBoolean("tem_placa"),
                    rs.getString("placa"),
                    rs.getString("tipo_combustivel"),
                    rs.getFloat("quilometragem"),
                    rs.getString("categoria_veiculo"),
                    rs.getLong("modelo_id"),
                    rs.getLong("proprietario_id"),
                    rs.getBoolean("ativo")
            ));
        }
        return lista;
    }
}
