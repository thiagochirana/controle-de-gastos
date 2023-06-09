package br.com.controlegastos.front.controller;

import br.com.controlegastos.controle.MarcaController;
import br.com.controlegastos.entidades.records.DadosCadastroMarca;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TelaModalCadastroMarcaController {

    String nomeMarca;

    String caminhoArquivo;

    @FXML
    private Button btnCloseModalMarca;

    @FXML
    private TextField TxFdNomeMarca;

    @FXML
    private Button btnFileChooserImgMarca;

    @FXML
    void abrirEscolharArquivo(MouseEvent event) {
        System.out.println("Abrindo modal");
    }

    @FXML
    void obterDadosMarca(MouseEvent event) {
        this.nomeMarca = TxFdNomeMarca.getText();
    }

    @FXML
    void fecharModal(MouseEvent event) {
        Stage stm = (Stage) btnCloseModalMarca.getScene().getWindow();
        stm.close();
    }

    public void salvarMarca() throws Exception {
        MarcaController marca = new MarcaController();
        String mensagem = marca.cadastrarMarca(new DadosCadastroMarca(nomeMarca, caminhoArquivo)).mensagem();
    }

}
