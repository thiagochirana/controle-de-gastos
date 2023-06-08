module br.com.controlegastos.controlegastosapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.slf4j;
    requires java.sql;
    requires org.flywaydb.core;

    opens br.com.controlegastos.front.view to javafx.fxml;
    exports br.com.controlegastos.front.view;
    exports br.com.controlegastos.front.controller;
    opens br.com.controlegastos.front.controller to javafx.fxml;
}