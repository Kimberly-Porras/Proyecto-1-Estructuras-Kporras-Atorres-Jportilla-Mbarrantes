/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.proyecto;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Universidad Nacional, Campus Coto
//Desarrollado por:
    //Joxan Portilla Hernandez
    //Melani Barrantes Hidalgo
    //Alberto Torres
    //Kimberly Porras
//2023

public class CuboController implements Initializable {

    Accesibilidad Accesib = new Accesibilidad();
    PrincipalController Princip = new PrincipalController();
    Cubo cubo = new Cubo();

    @FXML
    private GridPane grd_Cubo;

    int FilaArriba[] = new int[12];
    int FilaAbajo[] = new int[12];
    int FilaVIzquierda[] = new int[12];
    int FilaVDerecha[] = new int[12];

    @FXML
    private Rectangle R_00;
    @FXML
    private Rectangle R_10;
    @FXML
    private Rectangle R_20;
    @FXML
    private Rectangle R_01;
    @FXML
    private Rectangle R_11;
    @FXML
    private Rectangle R_21;
    @FXML
    private Rectangle R_02;
    @FXML
    private Rectangle R_12;
    @FXML
    private Rectangle R_22;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cubo.asignarValoresMatriz();
        pintarGridPane(1);
        asignarValoresVectoresHorizontales();
        asignarValoresVectoresVerticales();
    }

    public void asignarValoresVectoresHorizontales() {
        int contador1 = 0;
        int contador2 = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                FilaArriba[contador1] = cubo.cubo[i][j][0];
                contador1++;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                FilaAbajo[contador2] = cubo.cubo[i][j][2];
                contador2++;
            }
        }

        System.out.println("Vector FilaArriba");
        ImprimirVector(FilaArriba);
        System.out.println("Vector FilaAbajo");
        ImprimirVector(FilaAbajo);

    }

    public void asignarValoresVectoresVerticales() {

        int contador3 = 0;
        int contador4 = 0;

        int caras1[] = {1, 4, 3, 5}; // Caras que deseas llenar en ese orden
        int caras2[] = {1, 4, 3, 5};

        for (int i = 0; i < caras1.length; i++) { // Ciclo para recorrer las caras
            for (int j = 0; j < 3; j++) { // Ciclo para recorrer las posiciones en cada cara
                FilaVIzquierda[contador3] = cubo.cubo[caras1[i]][j][2];

                contador3++;
            }
        }

        for (int i = 0; i < caras2.length; i++) { // Ciclo para recorrer las caras
            for (int j = 0; j < 3; j++) { // Ciclo para recorrer las posiciones en cada cara
                FilaVDerecha[contador4] = cubo.cubo[caras2[i]][j][0];
                contador4++;
            }
        }

        System.out.println("Vector FilaIzquierda");
        ImprimirVector(FilaVIzquierda);
        System.out.println("Vector FilaDerecha");
        ImprimirVector(FilaVDerecha);
    }

    @FXML
    private void Volver(ActionEvent event) throws IOException { //Vuelve a la pantalla principal
        App.setRoot("Principal");
    }

    @FXML
    private void moverAbajo(ActionEvent event) {
        cubo.moverAbajo();
        pintarGridPane(1);
        System.out.println("----------------------------");
    }

    @FXML
    private void moverArriba(ActionEvent event) {
        cubo.moverArriba();
        pintarGridPane(1);
        System.out.println("----------------------------");
    }

    @FXML
    private void moverDerecha(ActionEvent event) {
        cubo.moverDerecha();
        pintarGridPane(1);
        System.out.println("----------------------------");
    }

    @FXML
    private void moverIzq(ActionEvent event) {
        cubo.moverIzq();
        pintarGridPane(1);
        System.out.println("----------------------------");
    }

    private Color obtenerColorPorValor(int valor) {
        switch (valor) {
            case 1:
                return Color.GREEN;
            case 2:
                return Color.RED;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.ORANGE;
            case 5:
                return Color.WHITE;
            case 6:
                return Color.YELLOW;
        }
        return null;
    }

    public void pintarGridPane(int cara) {
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                int valor = cubo.cubo[1][columna][fila];
                Color color = obtenerColorPorValor(valor);

                // Construye el selector CSS para buscar el nodo
                String selector = "#R_" + columna + fila;

                // Busca el nodo en el GridPane usando el selector CSS
                Node node = grd_Cubo.lookup(selector);

                if (node instanceof Rectangle) {
                    Rectangle rect = (Rectangle) node;
                    rect.setFill(color);
                }
            }
        }
    }

    public void ImprimirVector(int vector[]) {

        for (int i = 0; i < vector.length; i++) {

            System.out.println("|" + vector[i] + "|");

        }

    }

    public void CorrimientoCircularDerecha(int[] vector, int posiciones) {
        int longitud = vector.length;

        // Verifica que el número de posiciones a correr sea menor o igual a la longitud del vector
        if (posiciones <= longitud) {
            int[] copia = Arrays.copyOf(vector, longitud); // Copia el vector original

            // Realiza el corrimiento circular hacia la derecha
            for (int i = 0; i < longitud; i++) {
                int nuevaPosicion = (i + posiciones) % longitud;
                vector[nuevaPosicion] = copia[i];
            }
        } else {
            // Si el número de posiciones a correr es mayor que la longitud del vector, puedes manejarlo como desees (por ejemplo, lanzar una excepción).
            System.out.println("Error: El número de posiciones a correr es mayor que la longitud del vector.");
        }
    }

    public void CorrimientoCircularIzquierda(int[] vector, int posiciones) {
        int longitud = vector.length;

        // Verifica que el número de posiciones a correr sea menor o igual a la longitud del vector
        if (posiciones <= longitud) {
            int[] copia = Arrays.copyOf(vector, longitud); // Copia el vector original

            // Realiza el corrimiento circular hacia la izquierda
            for (int i = 0; i < longitud; i++) {
                int nuevaPosicion = (i - posiciones + longitud) % longitud;
                vector[nuevaPosicion] = copia[i];
            }
        } else {
            // Si el número de posiciones a correr es mayor que la longitud del vector, puedes manejarlo como desees (por ejemplo, lanzar una excepción).
            System.out.println("Error: El número de posiciones a correr es mayor que la longitud del vector.");
        }
    }

    public void ActualizarMatrizConVectorFAR() {

        int contador1 = 0;

        int contador3 = 0;
        int contador4 = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                cubo.cubo[i][j][0] = FilaArriba[contador1];
                contador1++;
            }
        }

    }

    public void ActualizarMatrizConVectorFAB() {
        int contador2 = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                cubo.cubo[i][j][2] = FilaAbajo[contador2];
                contador2++;
            }
        }
    }

    public void ActualizarMatrizConVectorFVD() {
        int contador4 = 0;
        int caras[] = {1, 4, 3, 5}; // Caras que deseas llenar en ese orden

        for (int i = 0; i < 4; i++) { // Ciclo para recorrer las caras
            for (int j = 0; j < 3; j++) { // Ciclo para recorrer las filas en cada cara
                cubo.cubo[caras[i]][2][j] = FilaVDerecha[contador4];
                contador4++;
            }
        }
    }

    public void ActualizarMatrizConVectorFVI() {
        int contador3 = 0;
        int caras[] = {1, 4, 3, 5}; // Caras que deseas llenar en ese orden

        for (int i = 0; i < 4; i++) { // Ciclo para recorrer las caras
            for (int j = 0; j < 3; j++) { // Ciclo para recorrer las filas en cada cara
                cubo.cubo[caras[i]][0][j] = FilaVIzquierda[contador3];
                contador3++;
            }
        }
    }

    @FXML
    private void Guardar(ActionEvent event) {
        Accesib.guardarMatriz(cubo.cubo, Princip.nombre);
    }

    @FXML
    private void Reiniciar(ActionEvent event) {
        cubo.asignarValoresMatriz();
        pintarGridPane(1);
    }

    @FXML
    private void GirarFilaArribaDerecha(ActionEvent event) {
        CorrimientoCircularDerecha(FilaArriba, 3);
        ActualizarMatrizConVectorFAR();
        pintarGridPane(1);
    }

    @FXML
    private void GirarFIlaArribaIzquierda(ActionEvent event) {
        CorrimientoCircularIzquierda(FilaArriba, 3);
        ActualizarMatrizConVectorFAR();
        pintarGridPane(1);
    }

    @FXML
    private void GirarFilaAbajoDerecha(ActionEvent event) {
        CorrimientoCircularDerecha(FilaAbajo, 3);
        ActualizarMatrizConVectorFAB();
        pintarGridPane(1);
    }

    @FXML
    private void GirarFIlaVerticalDerechaArriba(ActionEvent event) {
        CorrimientoCircularDerecha(FilaVDerecha, 3);
        ActualizarMatrizConVectorFVD();
        pintarGridPane(1);
    }

    @FXML
    private void GirarFilaVerticalIzquierdaAbjo(ActionEvent event) {
        CorrimientoCircularIzquierda(FilaVIzquierda, 3);
        ActualizarMatrizConVectorFVI();
        pintarGridPane(1);
    }

    @FXML
    private void GirarFilaVerticalDerechaAbajo(ActionEvent event) {
        CorrimientoCircularIzquierda(FilaVDerecha, 3);
        ActualizarMatrizConVectorFVD();
        pintarGridPane(1);
    }

    @FXML
    private void GirarFIlaAbajoIzquierda(ActionEvent event) {
        CorrimientoCircularIzquierda(FilaAbajo, 3);
        ActualizarMatrizConVectorFAB();
        pintarGridPane(1);
    }

    @FXML
    private void GirarFilaVerticalIzquierda(ActionEvent event) {
        CorrimientoCircularDerecha(FilaVIzquierda, 3);
        ActualizarMatrizConVectorFVI();
        pintarGridPane(1);
    }
}
