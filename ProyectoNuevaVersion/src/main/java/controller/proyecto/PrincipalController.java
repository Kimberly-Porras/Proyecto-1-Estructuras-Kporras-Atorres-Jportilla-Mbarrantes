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
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//Universidad Nacional, Campus Coto
//Desarrollado por:
    //Joxan Portilla Hernandez
    //Melani Barrantes Hidalgo
    //Alberto Torres
    //Kimberly Porras
//2023

public class PrincipalController implements Initializable {
    static String nombre = ""; //Para pasar el nombre por clases
    @FXML
    private TextField txt_nombreJugador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Salir(ActionEvent event) {
        System.exit(0);
    }


    @FXML
    private void PantallaAcercaDe(ActionEvent event) throws IOException {
        App.setRoot("AcercaDe");
    }

    @FXML
    private void OnBtnJugar(MouseEvent event) {
        
        nombre = txt_nombreJugador.getText();
        
        // Obtener el nombre del jugador ingresado
        String nombreJugador = txt_nombreJugador.getText();

        try {
            // Llamar al método de la clase Accesibilidad para generar el archivo
            Accesibilidad.generarArchivoTexto(nombreJugador);

            // Aquí puedes navegar a la siguiente pantalla o realizar otras acciones según tu lógica de juego
            try {
                App.setRoot("Cubo");
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar errores de navegación de pantalla aquí
            }
        } catch (IOException e) {
            // Mostrar una alerta de que el nombre ya existe
            mostrarAlerta("Nombre Existente", e.getMessage());
        }
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    @FXML
    private void ContinuarJuego(ActionEvent event) {
    }   
}
