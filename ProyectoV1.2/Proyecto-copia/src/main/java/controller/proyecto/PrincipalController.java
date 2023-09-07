/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.proyecto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Melani Barrantes
 */
public class PrincipalController implements Initializable {

    @FXML
    private TextField txt_nombre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void PantallaJuego(ActionEvent event) throws IOException {
        App.setRoot("Cubo");
    }

    @FXML
    private void PantallaAcercaDe(ActionEvent event) throws IOException {
        App.setRoot("AcercaDe");
    }
    
}
