module br.com.controlegastos.controlegastosapp {

    requires org.slf4j;
    requires java.sql;
    requires org.flywaydb.core;
    requires java.desktop;
    requires java.logging;

    opens br.com.controlegastos.front.view to javafx.fxml;
    exports br.com.controlegastos.front.view;
   
    opens br.com.controlegastos.front.controller to javafx.fxml;
}