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
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Melani Barrantes
 */
public class CuboController implements Initializable {

    @FXML
    private GridPane grd_Cubo;

    int cubo[][][] = new int[6][3][3];
    int posicion = 2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grd_Cubo.setStyle("-fx-background-color: red;"); //Siempre comienza con el color rojo
        asignarValoresMatriz();
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

    public void guardarMatriz(int valoresCubo[][][]) {
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
    private void Volver(ActionEvent event) throws IOException {
        App.setRoot("Principal");
    }

    @FXML
    private void moverAbajo(ActionEvent event) {
        if (posicion == 2) {
            grd_Cubo.setStyle("-fx-background-color: yellow;");
            posicion = 6;
        } else {
            if (posicion == 6) {
                grd_Cubo.setStyle("-fx-background-color: FF7000;");
                posicion = 4;
            } else {
                if (posicion == 4) {
                    grd_Cubo.setStyle("-fx-background-color: white;");
                    posicion = 5;
                } else {
                    if (posicion == 5) {
                        grd_Cubo.setStyle("-fx-background-color: red;");
                        posicion = 2;
                    }
                }
            }
        }

    }

    @FXML
    private void moverArriba(ActionEvent event) {
        if (posicion == 6) {
            grd_Cubo.setStyle("-fx-background-color: red;");
            posicion = 2;
        } else {
            if (posicion == 2) {
                grd_Cubo.setStyle("-fx-background-color: white;");
                posicion = 5;
            } else {
                if (posicion == 5) {
                    grd_Cubo.setStyle("-fx-background-color: FF7000;");
                    posicion = 4;
                } else {
                    if (posicion == 4) {
                        grd_Cubo.setStyle("-fx-background-color: yellow;");
                        posicion = 6;
                    }
                }
            }
        }
    }

    @FXML
    private void moverDerecha(ActionEvent event) {
        if (posicion == 2) {
            grd_Cubo.setStyle("-fx-background-color: blue;");
            posicion = 3;
        } else {
            if (posicion == 3) {
                grd_Cubo.setStyle("-fx-background-color: FF7000;");
                posicion = 4;
            } else {
                if (posicion == 4) {
                    grd_Cubo.setStyle("-fx-background-color: green;");
                    posicion = 1;
                } else {
                    if (posicion == 1) {
                        grd_Cubo.setStyle("-fx-background-color: red;");
                        posicion = 2;
                    }

                }
            }
        }
    }

    @FXML
    private void moverIzq(ActionEvent event) {
        if (posicion == 2) {
            grd_Cubo.setStyle("-fx-background-color: green;");
            posicion = 1;
        } else {
            if (posicion == 1) {
                grd_Cubo.setStyle("-fx-background-color: FF7000;");
                posicion = 4;
            } else {
                if (posicion == 4) {
                    grd_Cubo.setStyle("-fx-background-color: blue;");
                    posicion = 3;
                } else {
                    if (posicion == 3) {
                        grd_Cubo.setStyle("-fx-background-color: red;");
                        posicion = 2;
                    }

                }
            }
        }
    }

}
