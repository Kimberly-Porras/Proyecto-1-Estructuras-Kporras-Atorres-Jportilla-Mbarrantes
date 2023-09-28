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
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

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
    @FXML
    private GridPane grd_cara1;
    @FXML
    private GridPane grd_cara2;
    @FXML
    private GridPane grd_cara3;
    @FXML
    private GridPane grd_cara4;
    @FXML
    private GridPane grd_cara5;
    @FXML
    private GridPane grd_cara6;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cubo.asignarValoresMatriz();
        asignarValoresVectorFilaAbajo();
        asignarValoresVectorFilaArriba();
        asignarValoresVectorFilaVDerecha();
        asignarValoresVectorFilaVIzquierda();
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
        
       // pintarGridPane(1);
        //asignarValoresVectoresHorizontales();
        //asignarValoresVectoresVerticales();
    }

    public void asignarValoresVectorFilaArriba() {
        int contador1 = 0;
        

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                FilaArriba[contador1] = cubo.cubo[i][j][0];
                contador1++;
            }
        }


        System.out.println("Vector FilaArriba");
        ImprimirVector(FilaArriba);
      

    }


    public void asignarValoresVectorFilaAbajo() {
       
        int contador2 = 0;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                FilaAbajo[contador2] = cubo.cubo[i][j][2];
                contador2++;
            }
        }

        System.out.println("Vector FilaAbajo");
        ImprimirVector(FilaAbajo);

    }


public void PintarGridPane2(GridPane gp, int[][][] M, int cara) {
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            int valor = M[cara][col][row];
            Rectangle rectangle = new Rectangle();
            
            // Configura el tamaño y el color del Rectangle según los valores
            switch (valor) {
                case 1:
                    rectangle.setFill(Color.GREEN);
                    break;
                case 2:
                    rectangle.setFill(Color.RED);
                    break;
                case 3:
                    rectangle.setFill(Color.BLUE);
                    break;
                case 4:
                    rectangle.setFill(Color.ORANGE);
                    break;
                case 5:
                    rectangle.setFill(Color.WHITE);
                    break;
                case 6:
                    rectangle.setFill(Color.YELLOW);
                    break;
                default:
                    // Si el valor no coincide con ningún color conocido, usa un color predeterminado
                    rectangle.setFill(Color.GRAY);
                    break;
            }
            
            // Configura el tamaño del Rectangle
            double cellWidth = 130; // Ajusta el ancho de la celda según tus necesidades
            double cellHeight = 130; // Ajusta la altura de la celda según tus necesidades
            rectangle.setWidth(cellWidth);
            rectangle.setHeight(cellHeight);
            
            // Configura el borde negro
            rectangle.setStroke(Color.BLACK); // Color del borde
            rectangle.setStrokeWidth(2); // Grosor del borde
            rectangle.setStrokeType(StrokeType.INSIDE); // Tipo de trazo del borde (en el interior del rectángulo)

            gp.add(rectangle, col, row);
        }
    }
}


public void PintarGridPane3(GridPane gp, int[][][] M, int cara) {
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            int valor = M[cara][col][row];
            Rectangle rectangle = new Rectangle();
            
            // Configura el tamaño y el color del Rectangle según los valores
            switch (valor) {
                case 1:
                    rectangle.setFill(Color.GREEN);
                    break;
                case 2:
                    rectangle.setFill(Color.RED);
                    break;
                case 3:
                    rectangle.setFill(Color.BLUE);
                    break;
                case 4:
                    rectangle.setFill(Color.ORANGE);
                    break;
                case 5:
                    rectangle.setFill(Color.WHITE);
                    break;
                case 6:
                    rectangle.setFill(Color.YELLOW);
                    break;
                default:
                    // Si el valor no coincide con ningún color conocido, usa un color predeterminado
                    rectangle.setFill(Color.GRAY);
                    break;
            }
            
            // Configura el tamaño del Rectangle
            double cellWidth = 30; // Ajusta el ancho de la celda según tus necesidades
            double cellHeight = 30; // Ajusta la altura de la celda según tus necesidades
            rectangle.setWidth(cellWidth);
            rectangle.setHeight(cellHeight);
            
            // Configura el borde negro
            rectangle.setStroke(Color.BLACK); // Color del borde
            rectangle.setStrokeWidth(2); // Grosor del borde
            rectangle.setStrokeType(StrokeType.INSIDE); // Tipo de trazo del borde (en el interior del rectángulo)

            gp.add(rectangle, col, row);
        }
    }
}





    public void asignarValoresVectorFilaVIzquierda() {

        int contador3 = 0;


        int caras1[] = {1, 4, 3, 5}; // Caras que deseas llenar en ese orden
       

        for (int i = 0; i < caras1.length; i++) { // Ciclo para recorrer las caras
            for (int j = 0; j < 3; j++) { // Ciclo para recorrer las posiciones en cada cara
                FilaVIzquierda[contador3] = cubo.cubo[caras1[i]][j][2];

                contador3++;
            }
        }


        System.out.println("Vector FilaIzquierda");
        ImprimirVector(FilaVIzquierda);
     
    }
    
     public void asignarValoresVectorFilaVDerecha() {

        
        int contador4 = 0;
        int caras2[] = {1, 4, 3, 5};


        for (int i = 0; i < caras2.length; i++) { // Ciclo para recorrer las caras
            for (int j = 0; j < 3; j++) { // Ciclo para recorrer las posiciones en cada cara
                FilaVDerecha[contador4] = cubo.cubo[caras2[i]][j][0];
                contador4++;
            }
        }

    
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
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
        //pintarGridPane(1);
        System.out.println("----------------------------");
    }

    @FXML
    private void moverArriba(ActionEvent event) {
        cubo.moverArriba();
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
        //pintarGridPane(1);
        System.out.println("----------------------------");
    }

    @FXML
    private void moverDerecha(ActionEvent event) {
        cubo.moverDerecha();
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
        //pintarGridPane(1);
        System.out.println("----------------------------");
    }

    @FXML
    private void moverIzq(ActionEvent event) {
        cubo.moverIzq();
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
        //pintarGridPane(1);
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
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
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
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
    }

    public void ActualizarMatrizConVectorFAR() {

        int contador1 = 0;


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
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
    }

    @FXML
    private void GirarFilaArribaDerecha(ActionEvent event) {
        CorrimientoCircularDerecha(FilaArriba, 3);
        ActualizarMatrizConVectorFAR();
        cubo.trasponerMatriz(4, 1);
        //pintarGridPane(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo.cubo[i][k][j]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
        
    }

    @FXML
    private void GirarFIlaArribaIzquierda(ActionEvent event) {
        
        CorrimientoCircularIzquierda(FilaArriba, 3);
        ActualizarMatrizConVectorFAR();
        cubo.trasponerMatriz(4, 0);
        //pintarGridPane(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo.cubo[i][k][j]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
    }

    @FXML
    private void GirarFilaAbajoDerecha(ActionEvent event) {
        CorrimientoCircularDerecha(FilaAbajo, 3);
        ActualizarMatrizConVectorFAB();
        cubo.trasponerMatriz(5, 0);
        //pintarGridPane(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo.cubo[i][k][j]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
    }

    @FXML
    private void GirarFIlaVerticalDerechaArriba(ActionEvent event) {
        CorrimientoCircularDerecha(FilaVDerecha, 3);
        ActualizarMatrizConVectorFVD();
        cubo.trasponerMatriz(2, 1);
        //pintarGridPane(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo.cubo[i][k][j]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
    }

    @FXML
    private void GirarFilaVerticalIzquierdaAbjo(ActionEvent event) {
        CorrimientoCircularIzquierda(FilaVIzquierda, 3);
        ActualizarMatrizConVectorFVI();
        cubo.trasponerMatriz(0, 0);
        //pintarGridPane(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo.cubo[i][k][j]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
    }

    @FXML
    private void GirarFilaVerticalDerechaAbajo(ActionEvent event) {
        CorrimientoCircularIzquierda(FilaVDerecha, 3);
        ActualizarMatrizConVectorFVD();
        cubo.trasponerMatriz(2, 1);
        //pintarGridPane(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo.cubo[i][k][j]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
    }

    @FXML
    private void GirarFIlaAbajoIzquierda(ActionEvent event) {
        CorrimientoCircularIzquierda(FilaAbajo, 3);
        ActualizarMatrizConVectorFAB();
        cubo.trasponerMatriz(5, 1);
        //pintarGridPane(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo.cubo[i][k][j]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
    }

    @FXML
    private void GirarFilaVerticalIzquierda(ActionEvent event) {
        
        CorrimientoCircularDerecha(FilaVIzquierda, 3);
        ActualizarMatrizConVectorFVI();
        cubo.trasponerMatriz(0, 1);
        //pintarGridPane(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo.cubo[i][k][j]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        PintarGridPane2(grd_Cubo,cubo.cubo,1);
        PintarGridPane3(grd_cara1,cubo.cubo,0);
        PintarGridPane3(grd_cara2,cubo.cubo,1);
        PintarGridPane3(grd_cara3,cubo.cubo,2);
        PintarGridPane3(grd_cara4,cubo.cubo,3);
        PintarGridPane3(grd_cara5,cubo.cubo,4);
        PintarGridPane3(grd_cara6,cubo.cubo,5);
    }
}
