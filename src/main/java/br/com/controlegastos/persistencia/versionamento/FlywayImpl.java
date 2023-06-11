package br.com.controlegastos.persistencia.versionamento;

import br.com.controlegastos.persistencia.propriedades.Propriedade;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlywayImpl {

    private static Logger LOG = LoggerFactory.getLogger(FlywayImpl.class);

    public static void start() throws Exception {
        try{
            LOG.info("Inicio da execução do Flyway");
            StringBuilder banco = new StringBuilder();
            banco.append(Propriedade.getValor("persistencia.url"));
            banco.append(Propriedade.getValor("persistencia.banco"));
            String usuario = Propriedade.getValor("persistencia.usuario");
            String senha = Propriedade.getValor("persistencia.senha");

            String tabelaBanco;
            try {
                tabelaBanco = Propriedade.getValor("flyway.tabela.banco");
                LOG.debug("Tabela que será de base para o flyway/versionamento: "+tabelaBanco);
            } catch (Exception e) {
                LOG.warn("Não encontrado o campo \"flyway.realizar.versionamento.no.restart\" no config.properties."+
                        "Setarei a tabela para padrão \"schema_version\"");
                tabelaBanco = "schema_version";
            }

            LOG.debug("Inicio implementacao Flyway.");
            Flyway flyway = Flyway.configure()
                    .dataSource(banco.toString(), usuario, senha)
                    .locations("classpath:db/migration")
                    .table(tabelaBanco)
                    .baselineOnMigrate(true)
                    .load();

            try{
                boolean versionamentoAtivo = Boolean.parseBoolean(Propriedade.getValor("flyway.versionamento"));
                LOG.debug("Aplicar patches? "+versionamentoAtivo);
                if (versionamentoAtivo) {
                    LOG.debug("Iniciando a aplicação de patches");
                    flyway.migrate();
                } else {
                    LOG.debug("Aplicacao de patches não está ativo. Para ativar, coloque \"true\" no campo \"flyway.versionamento\"");
                }
            }catch (Exception pe){
                LOG.warn("Houve um erro ao tentar executar a aplicação de migrações do Flyway.",pe);
            }

            try{
                boolean versionamentoAoReiniciar = Boolean.parseBoolean(Propriedade.getValor("flyway.realizar.versionamento.no.restart"));
                LOG.debug("Reaplicacao de patches ao reiniciar? "+versionamentoAoReiniciar);
                if (versionamentoAoReiniciar){
                    LOG.debug("Realizando a reaplicação de patches");
                    flyway.baseline();
                } else {
                    LOG.debug("Patches ao reiniciar não serão reaplicados");
                }
            }catch (Exception pe){
                LOG.warn("Não encontrado o campo \"flyway.realizar.versionamento.no.restart\" no config.properties. Logo a reaplicação de patches será FALSE");
                LOG.error("Erro ao executar a verificacao de versionamento.",pe);
            }

        }catch (FlywayException fe){
            throw new Exception("Houve um erro ao carregar o Flyway.",fe);
        }
    }
}
