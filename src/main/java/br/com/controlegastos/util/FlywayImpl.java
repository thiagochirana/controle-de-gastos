package br.com.controlegastos.util;

import org.flywaydb.core.Flyway;

import java.util.Properties;

public class FlywayImpl {

    public static void executar(boolean ativo) throws Exception {



        if(ativo){
            Flyway flyway = Flyway.configure()
                    .dataSource("jdbc:postgresql://localhost:5432/teste_database", "postgres", "postgres")
                    .locations("db/migration")
                    //.baselineOnMigrate(true)
                    .load();
            flyway.baseline();
            flyway.migrate();
        }
    }
}
