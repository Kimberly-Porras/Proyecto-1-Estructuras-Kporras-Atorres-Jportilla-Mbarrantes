/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.proyecto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Melani Barrantes
 */
public class CuboController implements Initializable {

    @FXML
    private GridPane grd_Cubo;

    int cubo[][][] = new int[6][3][3];
    int aux[][] = new int[3][3];

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
        asignarValoresMatriz();
        pintarGridPane(1);
    }

    public void asignarValoresMatriz() { //Rellena la matriz con numeros del 1 al 6 para asignar colores.
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    switch (i) {
                        case 0:
                            cubo[i][j][k] = 1;
                            break;
                        case 1:
                            cubo[i][j][k] = 2;
                            aux[j][k] = 2;
                            break;
                        case 2:
                            cubo[i][j][k] = 3;
                            break;
                        case 3:
                            cubo[i][j][k] = 4;
                            break;
                        case 4:
                            cubo[i][j][k] = 5;
                            break;
                        case 5:
                            cubo[i][j][k] = 6;
                            break;
                    }
                }
            }
        }
        guardarMatriz(cubo);
    }

    public void guardarMatriz(int valoresCubo[][][]) { //Guarda la matriz en un txt
        // Nombre del archivo que vamos a crear. Si no exite lo crea y si existe escribe
        String nombreArchivo = "CUBO.txt";

        //****SOBRESCRIBE*****
        try {
            // Abrimos el archivo en modo escritura
            FileWriter escritor = new FileWriter(nombreArchivo);

            // Creamos un BufferedWriter para escribir datos de manera eficiente
            BufferedWriter bufferEscritura = new BufferedWriter(escritor);

            // Escribimos texto en el archivo. Sobrescribe.
            for (int i = 0; i < 6; i++) {
                if (i == 0) {
                    bufferEscritura.write("Izquierda\n");
                } else {
                    if (i == 1) {
                        bufferEscritura.write("Frente\n");
                    } else {
                        if (i == 2) {
                            bufferEscritura.write("Derecha\n");
                        } else {
                            if (i == 3) {
                                bufferEscritura.write("Atras\n");
                            } else {
                                if (i == 4) {
                                    bufferEscritura.write("Arriba\n");
                                } else {
                                    if (i == 5) {
                                        bufferEscritura.write("Abajo\n");
                                    }
                                }
                            }
                        }
                    }
                }
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        bufferEscritura.write("| " + cubo[i][j][k]);
                        //System.out.print("| " + matriz[i][j][k]);
                    }
                    bufferEscritura.write("\n");
                }
                bufferEscritura.write("\n");
            }

            // Cerramos el archivo
            bufferEscritura.close();

        } catch (IOException e) {
            //En caso de error al escribir en el archivo, manejamos la excepción
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    @FXML
    private void Volver(ActionEvent event) throws IOException { //Vuelve a la pantalla principal
        App.setRoot("Principal");
    }

    @FXML
    private void moverAbajo(ActionEvent event) {
        for (int i = 0; i < 3; i++) { //Cara presentable pasarla a aux para mover matriz
            for (int j = 0; j < 3; j++) {
                aux[i][j] = cubo[1][i][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[1][j][k] = cubo[5][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[5][j][k] = cubo[3][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[3][j][k] = cubo[4][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[4][j][k] = aux[j][k];
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo[i][j][k]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        
        for (int i = 0; i < 3; i++) { //Cara presentable pasarla a aux para mover matriz
            for (int j = 0; j < 3; j++) {
                aux[i][j] = cubo[1][i][j];
            }
        }

        pintarGridPane(1);
        System.out.println("----------------------------");
    }

    @FXML
    private void moverArriba(ActionEvent event) {
        for (int i = 0; i < 3; i++) { //Cara presentable pasarla a aux para mover matriz
            for (int j = 0; j < 3; j++) {
                aux[i][j] = cubo[1][i][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[1][j][k] = cubo[4][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[4][j][k] = cubo[3][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[3][j][k] = cubo[5][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[5][j][k] = aux[j][k];
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo[i][j][k]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        
        for (int i = 0; i < 3; i++) { //Cara presentable pasarla a aux para mover matriz
            for (int j = 0; j < 3; j++) {
                aux[i][j] = cubo[1][i][j];
            }
        }

        pintarGridPane(1);
        System.out.println("----------------------------");
    }

    @FXML
    private void moverDerecha(ActionEvent event) {
        for (int i = 0; i < 3; i++) { //Cara presentable pasarla a aux para mover matriz
            for (int j = 0; j < 3; j++) {
                aux[i][j] = cubo[1][i][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[1][j][k] = cubo[2][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[2][j][k] = cubo[3][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[3][j][k] = cubo[0][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[0][j][k] = aux[j][k];
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo[i][j][k]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }

        for (int i = 0; i < 3; i++) { //Cara presentable pasarla a aux para mover matriz
            for (int j = 0; j < 3; j++) {
                aux[i][j] = cubo[1][i][j];
            }
        }

        pintarGridPane(1);
        System.out.println("----------------------------");
    }

    @FXML
    private void moverIzq(ActionEvent event) {
        for (int i = 0; i < 3; i++) { //Cara presentable pasarla a aux para mover matriz
            for (int j = 0; j < 3; j++) {
                aux[i][j] = cubo[1][i][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[1][j][k] = cubo[0][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[0][j][k] = cubo[3][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[3][j][k] = cubo[2][j][k];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[2][j][k] = aux[j][k];
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo[i][j][k]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
        
        for (int i = 0; i < 3; i++) { //Cara presentable pasarla a aux para mover matriz
            for (int j = 0; j < 3; j++) {
                aux[i][j] = cubo[1][i][j];
            }
        }

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
                int valor = cubo[1][fila][columna];
                Color color = obtenerColorPorValor(valor);

                // Construye el selector CSS para buscar el nodo
                String selector = "#R_" + fila + columna;

                // Busca el nodo en el GridPane usando el selector CSS
                Node node = grd_Cubo.lookup(selector);

                if (node instanceof Rectangle) {
                    Rectangle rect = (Rectangle) node;
                    rect.setFill(color);
                }
            }
        }
    }

    @FXML
    private void Guardar(ActionEvent event) {
        guardarMatriz(cubo);
    }

    @FXML
    private void Reiniciar(ActionEvent event) {
        asignarValoresMatriz();
        pintarGridPane(1);
    }
}
