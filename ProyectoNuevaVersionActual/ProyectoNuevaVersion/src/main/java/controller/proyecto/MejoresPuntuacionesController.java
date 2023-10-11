/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.proyecto;

import static controller.proyecto.Accesibilidad.leerMejoresPuntuacionesDesdeArchivo;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

//Universidad Nacional, Campus Coto
//Desarrollado por:
    //Joxan Portilla Hernandez
    //Melani Barrantes Hidalgo
    //Alberto Torres
    //Kimberly Porras
//2023

public class MejoresPuntuacionesController implements Initializable {
    
    @FXML
    private TableColumn<MejoresPuntuaciones, String> ColNombreMov;
    @FXML
    private TableColumn<MejoresPuntuaciones, Integer> ColMovimienos;
    @FXML
    private TableColumn<MejoresPuntuaciones, String> ColNombreTiem;
    @FXML
    private TableColumn<MejoresPuntuaciones, String> ColTiempo;
    @FXML
    private TableView<MejoresPuntuaciones> tbvMenoresMov;
    @FXML
    private TableView<MejoresPuntuaciones> tbvMejoresTiempos;
    
    Accesibilidad ac = new Accesibilidad();
    
    ObservableList ListaMejoresPuntuaciones = FXCollections.observableArrayList();
    @FXML
    private Label lbl_tit1;
    @FXML
    private Label lbl_tit2;
    @FXML
    private Label lbl_tit3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        MejoresTiempo();
        MenoresMovimientos();
    }    
    
    public void MejoresTiempo() {
        List<MejoresPuntuaciones> listaMejoresPuntuaciones = leerMejoresPuntuacionesDesdeArchivo();

        // Llenar la tabla de Mejores Tiempos
        ObservableList<MejoresPuntuaciones> observableListaMejoresTiempos = FXCollections.observableArrayList(listaMejoresPuntuaciones);
        ColNombreTiem.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        ColTiempo.setCellValueFactory(new PropertyValueFactory<>("Tiempo"));
        tbvMejoresTiempos.setItems(observableListaMejoresTiempos);
    }
    
    public void MenoresMovimientos() {
        List<MejoresPuntuaciones> listaMejoresPuntuaciones = leerMejoresPuntuacionesDesdeArchivo();

        // Llenar la tabla de Menores Movimientos
        ObservableList<MejoresPuntuaciones> observableListaMenoresMovimientos = FXCollections.observableArrayList(listaMejoresPuntuaciones);
        ColNombreMov.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        ColMovimienos.setCellValueFactory(new PropertyValueFactory<>("Movimientos"));
        tbvMenoresMov.setItems(observableListaMenoresMovimientos);
    }
    
    @FXML
    private void volver(ActionEvent event) throws IOException {
        App.setRoot("Principal");
    }    
}
