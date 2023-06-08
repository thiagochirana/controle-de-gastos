package br.com.controlegastos.front.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TelaModalCadastroMarcaController {

    @FXML
    private Button btnCloseModalMarca;

    @FXML
    private Button btnFileChooserImgMarca;

    @FXML
    void abrirEscolharArquivo(MouseEvent event) {
        System.out.println("Abrindo modal");
    }

    @FXML
    void fecharModal(MouseEvent event) {
        Stage stm = (Stage) btnCloseModalMarca.getScene().getWindow();
        stm.close();
    }

}
