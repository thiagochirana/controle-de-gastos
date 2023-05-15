package br.com.controlegastos.persistencia.versionamento;

import br.com.controlegastos.persistencia.propriedades.Config;
import br.com.controlegastos.persistencia.propriedades.PropriedadeException;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FlywayImpl {

    private static Logger LOG = LoggerFactory.getLogger(FlywayImpl.class);

    public static void start() throws Exception {
        try{
            LOG.info("Inicio da execução do Flyway");
            StringBuilder banco = new StringBuilder();
            banco.append(Config.obterPropriedade("persistencia.url"));
            banco.append(Config.obterPropriedade("persistencia.banco"));
            String usuario = Config.obterPropriedade("persistencia.usuario");
            String senha = Config.obterPropriedade("persistencia.senha");

            String tabelaBanco;
            try {
                tabelaBanco = Config.obterPropriedade("flyway.tabela.banco");
                LOG.debug("Tabela que será de base para o flyway/versionamento: "+tabelaBanco);
            } catch (PropriedadeException e) {
                LOG.warn("Não encontrado o campo \"flyway.realizar.versionamento.no.restart\" no config.properties."+
                        "Setarei a tabela para padrão \"schema_version\"");
                tabelaBanco = "schema_version";
            }

            LOG.debug("Inicio implementacao Flyway.");
            Flyway flyway = Flyway.configure()
                    .dataSource(banco.toString(), usuario, senha)
                    .locations("classpath:db/patches")
                    .table(tabelaBanco)
                    .load();

            try{
                boolean versionamentoAoReiniciar = Boolean.parseBoolean(Config.obterPropriedade("flyway.realizar.versionamento.no.restart"));
                LOG.debug("Reaplicacao de patches ao reiniciar? "+versionamentoAoReiniciar);
                if (versionamentoAoReiniciar){
                    LOG.debug("Realizando a reaplicação de patches");
                    flyway.baseline();
                } else {
                    LOG.debug("Patches ao reiniciar não serão reaplicados");
                }
            }catch (PropriedadeException pe){
                LOG.warn("Não encontrado o campo \"flyway.realizar.versionamento.no.restart\" no config.properties. Logo a reaplicação de patches será FALSE");
                LOG.error(pe.getCause().toString());
            }

            try{
                boolean versionamentoAtivo = Boolean.parseBoolean(Config.obterPropriedade("flyway.versionamento"));
                LOG.debug("Aplicar patches? "+versionamentoAtivo);
                if (versionamentoAtivo) {
                    LOG.debug("Iniciando a aplicação de patches");
                    flyway.migrate();
                } else {
                    LOG.debug("Aplicacao de patches não está ativo. Para ativar, coloque \"true\" no campo \"flyway.versionamento\"");
                }
            }catch (PropriedadeException pe){
                LOG.warn("Não encontrado o campo \"flyway.versionamento\" no config.properties. Logo o versionamento ativo será FALSE");
                LOG.error(pe.getCause().toString());
            }

        }catch (PropriedadeException pe){
            LOG.error("Houve um erro ao carregar propriedades no Flyway: "+pe);
            throw new PropriedadeException("Houve um erro ao carregar propriedades no Flyway.", pe);
        }catch (IOException io){
            throw io;
        }catch (FlywayException fe){
            throw new FlywayException("Nao foi possivel realizar o versionamento Flyway.",fe);
        }
    }
}
