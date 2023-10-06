/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.proyecto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 * *
 * Año 2023 Universidad Nacional, Campus Coto
 *
 * @author Joxan Portilla Hernandez
 * @author Melani Barrantes Hidalgo
 * @author Alberto Torres
 * @author Kimberly Porras
 */
public class CuboController implements Initializable {

    Accesibilidad Accesib = new Accesibilidad();
    PrincipalController Princip = new PrincipalController();
    Cubo cubo = new Cubo();
    final int CARAS = 6, FILAS = 3, COLUMNAS = 3;
    int i, j, k;
    int caras[] = {1, 4, 3, 5}; // Caras para arriba y abajo

    Stack movimientos = new Stack();

    @FXML
    private GridPane grd_Cubo;
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
    @FXML
    private Rectangle R_11;
    @FXML
    private Rectangle R_12;
    @FXML
    private Rectangle R_22;
    @FXML
    private Rectangle R_00;
    @FXML
    private Rectangle R_10;
    @FXML
    private Rectangle R_20;
    @FXML
    private Rectangle R_01;
    @FXML
    private Rectangle R_02;
    @FXML
    private Rectangle R_21;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReiniciarTodo();
    }

    public void ReiniciarTodo() { //Metodo para reiciar el juego
        cubo.asignarValoresMatriz();
        PintarTodo();
    }

    public Color ObtenerColor(int valor) { //Función para obtener el color segun un valor
        Color color;
        switch (valor) {
            case 1:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.RED;
                break;
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.ORANGE;
                break;
            case 5:
                color = Color.WHITE;
                break;
            case 6:
                color = Color.YELLOW;
                break;
            default:
                color = Color.GRAY;
                break;
        }

        return color;
    }

    public void PintarTodo() { //Método que pinta los rectangulos en el panel
        PintarGridPane2(grd_Cubo, cubo.cubo, 1);
        PintarGridPane3(grd_cara1, cubo.cubo, 0);
        PintarGridPane3(grd_cara2, cubo.cubo, 1);
        PintarGridPane3(grd_cara3, cubo.cubo, 2);
        PintarGridPane3(grd_cara4, cubo.cubo, 3);
        PintarGridPane3(grd_cara5, cubo.cubo, 4);
        PintarGridPane3(grd_cara6, cubo.cubo, 5);
    }

    public void Imprimir(int cuboactual[][][]) { //Método para imprimir en consola el cubo
        System.out.println("INICIAR A IMPRIMIR");
        for (i = 0; i < CARAS; i++) {
            System.out.println("CARA " + i);
            for (j = 0; j < FILAS; j++) {
                System.out.print("F" + j + " = ");
                for (k = 0; k < COLUMNAS; k++) {
                    System.out.print(cuboactual[i][j][k] + " | ");
                }
                System.out.println("");
            }
        }

        System.out.println("FINALIZA DE IMPRIMIR");
    }

    public void MoverCaraDerecha() { //Método para mover una cara completa a la derecha
        int[][] aux = new int[FILAS][COLUMNAS];
        int[][][] actual = cubo.getCubo().clone();
        for (j = 0; j < FILAS; j++) {
            for (k = 0; k < COLUMNAS; k++) {
                aux[j][k] = cubo.getCubo()[0][j][k];
            }
        }

        for (i = 0; i < CARAS - 2; i++) {
            for (j = 0; j < FILAS; j++) {
                for (k = 0; k < COLUMNAS; k++) {
                    if (i < CARAS - 3) {
                        actual[i][j][k] = actual[i + 1][j][k];
                    } else {
                        actual[CARAS - 3][j][k] = aux[j][k];
                    }
                }
            }
        }
        cubo.setCubo(actual);
    }

    public void MoverCaraIzquierda() { //Método para mover una cara completa a la izquierda
        int[][] aux = new int[FILAS][COLUMNAS];
        int[][][] actual = cubo.getCubo().clone();
        for (j = 0; j < FILAS; j++) {
            for (k = 0; k < COLUMNAS; k++) {
                aux[j][k] = cubo.getCubo()[3][j][k];
            }
        }

        for (i = CARAS - 3; i >= 0; i--) {
            for (j = FILAS - 1; j >= 0; j--) {
                for (k = COLUMNAS - 1; k >= 0; k--) {
                    if (i > 0) {
                        actual[i][j][k] = actual[i - 1][j][k];
                    } else {
                        actual[0][j][k] = aux[j][k];
                    }
                }
            }
        }
        cubo.setCubo(actual);
    }

    public void MoverCaraArriba() { //Método para mover una cara completa hacia arriba
        int[][] aux = new int[FILAS][COLUMNAS];
        int[][][] actual = cubo.getCubo().clone();
        for (j = 0; j < FILAS; j++) {
            for (k = 0; k < COLUMNAS; k++) {
                aux[j][k] = cubo.getCubo()[caras[0]][j][k];
            }
        }

        for (i = 0; i < CARAS - 2; i++) {
            for (j = 0; j < FILAS; j++) {
                for (k = 0; k < COLUMNAS; k++) {
                    if (i < CARAS - 3) {
                        actual[caras[i]][j][k] = actual[caras[i + 1]][j][k];
                    } else {
                        actual[caras[CARAS - 3]][j][k] = aux[j][k];
                    }
                }
            }
        }
        cubo.setCubo(actual);
    }

    public void MoverCaraAbajo() { //Método para mover una cara completa hacia abajo
        int[][] aux = new int[FILAS][COLUMNAS];
        int[][][] actual = cubo.getCubo().clone();
        for (j = 0; j < FILAS; j++) {
            for (k = 0; k < COLUMNAS; k++) {
                aux[j][k] = cubo.getCubo()[caras[3]][j][k];
            }
        }

        for (i = CARAS - 3; i >= 0; i--) {
            for (j = FILAS - 1; j >= 0; j--) {
                for (k = COLUMNAS - 1; k >= 0; k--) {
                    if (i > 0) {
                        actual[caras[i]][j][k] = actual[caras[i - 1]][j][k];
                    } else {
                        actual[caras[0]][j][k] = aux[j][k];
                    }
                }
            }
        }
        cubo.setCubo(actual);
    }

    public void GirarIzquierda(int index) { //Método para hacer un giro a la izquierda
        int[] aux = new int[FILAS];
        int[][][] actual = cubo.getCubo().clone();

        for (j = 0; j < FILAS; j++) {
            aux[j] = cubo.getCubo()[0][j][index];
        }

        for (i = 0; i < CARAS - 2; i++) {
            for (j = 0; j < FILAS; j++) {
                if (i < CARAS - 3) {
                    actual[i][j][index] = actual[i + 1][j][index];
                } else {
                    actual[CARAS - 3][j][index] = aux[j];
                }
            }
        }
        cubo.setCubo(actual);
    }

    public void GirarDerecha(int index) { //Método para girar a la derecha
        int[] aux = new int[FILAS];
        int[][][] actual = cubo.getCubo().clone();
        for (j = 0; j < FILAS; j++) {
            aux[j] = cubo.getCubo()[3][j][index];
        }

        for (i = CARAS - 3; i >= 0; i--) {
            for (j = FILAS - 1; j >= 0; j--) {

                if (i > 0) {
                    actual[i][j][index] = actual[i - 1][j][index];
                } else {
                    actual[0][j][index] = aux[j];
                }

            }
        }
        cubo.setCubo(actual);
    }

    public void GirarAbajo(int index) { //Método para girar hacia abajo
        int[] aux = new int[COLUMNAS];
        int[][][] actual = cubo.getCubo().clone();

        for (k = 0; k < COLUMNAS; k++) {
            aux[k] = cubo.getCubo()[caras[0]][index][k];
        }

        for (i = 0; i < CARAS - 2; i++) {
            for (k = 0; k < COLUMNAS; k++) {
                if (caras[i] == 3) {
                    if (index == 2) {
                        actual[caras[i]][0][k] = actual[caras[i + 1]][index][k];
                    } else if (index == 0) {
                        actual[caras[i]][2][k] = actual[caras[i + 1]][index][k];
                    } else {
                        actual[caras[i]][index][k] = actual[caras[i + 1]][index][k];
                    }
                } else if (caras[i] == 4) {
                    if (index == 2) {
                        actual[caras[i]][index][k] = actual[caras[i + 1]][0][k];
                    } else if (index == 0) {
                        actual[caras[i]][index][k] = actual[caras[i + 1]][2][k];
                    } else {
                        actual[caras[i]][index][k] = actual[caras[i + 1]][index][k];
                    }
                } else {
                    if (i < CARAS - 3) {
                        actual[caras[i]][index][k] = actual[caras[i + 1]][index][k];
                    } else {
                        actual[caras[CARAS - 3]][index][k] = aux[k];
                    }
                }
            }
        }
        cubo.setCubo(actual);
    }

    public void GirarArriba(int index) { //Método para girar hacia arriba
        int[] aux = new int[COLUMNAS];
        int[][][] actual = cubo.getCubo().clone();
        for (k = 0; k < COLUMNAS; k++) {
            aux[k] = cubo.getCubo()[caras[3]][index][k];
        }

        for (i = CARAS - 3; i >= 0; i--) {
            for (k = COLUMNAS - 1; k >= 0; k--) {
                if (caras[i] == 3) {
                    if (index == 2) {
                        actual[caras[i]][0][k] = actual[caras[i - 1]][index][k];
                    } else if (index == 0) {
                        actual[caras[i]][2][k] = actual[caras[i - 1]][index][k];
                    } else {
                        actual[caras[i]][index][k] = actual[caras[i - 1]][index][k];
                    }
                } else if (caras[i] == 5) {
                    if (index == 2) {
                        actual[caras[i]][2][k] = actual[caras[i - 1]][0][k];
                    } else if (index == 0) {
                        actual[caras[i]][0][k] = actual[caras[i - 1]][2][k];
                    } else {
                        actual[caras[i]][index][k] = actual[caras[i - 1]][index][k];
                    }
                } else {
                    if (i > 0) {
                        actual[caras[i]][index][k] = actual[caras[i - 1]][index][k];
                    } else {
                        actual[caras[0]][index][k] = aux[k];
                    }
                }

            }
            cubo.setCubo(actual);
        }
    }

    public void Transponer(int cara, boolean derecha) { //Método para transponer una cara
        int[][] aux = new int[FILAS][COLUMNAS];
        int[][] transpuesta = new int[FILAS][COLUMNAS];
        int[][][] actual = cubo.getCubo().clone();
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                aux[j][i] = actual[cara][j][i];
            }
        }

        if (derecha) {
            for (i = 0; i < FILAS; i++) {
                for (j = 0; j < COLUMNAS; j++) {
                    transpuesta[j][i] = aux[FILAS - 1 - i][j];
                }
            }

            aux = transpuesta;
        } else {
            for (i = 0; i < FILAS; i++) {
                for (j = 0; j < COLUMNAS; j++) {
                    transpuesta[j][i] = aux[i][COLUMNAS - 1 - j];
                }
            }
            aux = transpuesta;
        }

        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                actual[cara][i][j] = aux[i][j];
            }
        }

        cubo.setCubo(actual);
    }

    public void PintarGridPane2(GridPane gp, int[][][] cuboactual, int cara) { //Método para pintar el cubo de forma visual (cara actual)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int valor = cuboactual[cara][col][row];
                Rectangle rectangle = new Rectangle();

                // Configura el tamaño y el color del Rectangle según los valores
                rectangle.setFill(ObtenerColor(valor));

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

    public void PintarGridPane3(GridPane gp, int[][][] cuboactual, int cara) { //Método para pintar el cubo de forma visual (caras pequeñas)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int valor = cuboactual[cara][col][row];
                Rectangle rectangle = new Rectangle();

                // Configura el tamaño y el color del Rectangle según los valores
                rectangle.setFill(ObtenerColor(valor));

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

    @FXML
    private void Volver(ActionEvent event) throws IOException { //Vuelve a la pantalla principal
        App.setRoot("Principal");
    }

    @FXML
    private void moverAbajo(ActionEvent event) {
        MoverCaraAbajo();
        Transponer(0, true);
        Transponer(2, false);
        PintarTodo();
        movimientos.push("Abajo");
    }

    @FXML
    private void moverArriba(ActionEvent event) {
        MoverCaraArriba();
        Transponer(0, true);
        Transponer(2, false);
        PintarTodo();
        movimientos.push("Arriba");
    }

    @FXML
    private void moverDerecha(ActionEvent event) {
        MoverCaraDerecha();
        Transponer(4, false);
        Transponer(5, true);
        PintarTodo();
        movimientos.push("Derecha");
    }

    @FXML
    private void moverIzq(ActionEvent event) {
        MoverCaraIzquierda();
        Transponer(4, true);
        Transponer(5, false);
        PintarTodo();
        movimientos.push("Izquierda");
    }

    @FXML
    private void Guardar(ActionEvent event) {
        Accesib.guardarMatriz(cubo.cubo, Princip.nombre, movimientos);
    }

    @FXML
    private void Reiniciar(ActionEvent event) {
        ReiniciarTodo();
    }

    @FXML
    private void GirarFilaArribaDerecha(ActionEvent event) {
        GirarDerecha(0);
        Transponer(4, true);
        PintarTodo();
        movimientos.push("FilaArribaDerecha");
    }

    @FXML
    private void GirarFIlaArribaIzquierda(ActionEvent event) {
        GirarIzquierda(0);
        Transponer(4, false);
        PintarTodo();
        movimientos.push("FilaArribaIzquierda");
    }

    @FXML
    private void GirarFilaAbajoDerecha(ActionEvent event) {
        GirarDerecha(2);
        Transponer(5, false);
        PintarTodo();
        movimientos.push("FilaAbajoDerecha");
    }

    @FXML
    private void GirarFIlaVerticalDerechaArriba(ActionEvent event) { //revisar
        GirarArriba(2);
        Transponer(2, false);
        PintarTodo();
        movimientos.push("FilaVerticalDerechaArriba");
    }

    @FXML
    private void GirarFilaVerticalIzquierdaAbjo(ActionEvent event) { //revisar
        GirarAbajo(0);
        Transponer(0, false);
        PintarTodo();
        movimientos.push("FilaVerticalIzquierdaAbajo");
    }

    @FXML
    private void GirarFilaVerticalDerechaAbajo(ActionEvent event) { //revisar
        GirarAbajo(2);
        Transponer(2, true);
        PintarTodo();
        movimientos.push("FilaVerticalDerechaAbajo");
    }

    @FXML
    private void GirarFIlaAbajoIzquierda(ActionEvent event) {
        GirarIzquierda(2);
        Transponer(5, true);
        PintarTodo();
        movimientos.push("FilaAbajoIzquierda");
    }

    @FXML
    private void GirarFilaVerticalIzquierda(ActionEvent event) {
        GirarArriba(0);
        Transponer(0, true);
        PintarTodo();
        movimientos.push("FilaVerticalIzquierda");
    }

    @FXML
    private void ResolverSolo(ActionEvent event) {
        resolverSolo();
    }

    private void resolverSolo() {
        Thread resolverThread = new Thread(() -> {
            while (!movimientos.empty()) {
                String mov = (String) movimientos.pop();

                switch (mov) {
                    case "Derecha":
                        MoverCaraIzquierda();
                        Transponer(4, true);
                        Transponer(5, false);
                        break;
                    case "Izquierda":
                        MoverCaraDerecha();
                        Transponer(4, false);
                        Transponer(5, true);
                        break;
                    case "Arriba":
                        MoverCaraAbajo();
                        Transponer(0, true);
                        Transponer(2, false);
                        break;
                    case "Abajo":
                        MoverCaraArriba();
                        Transponer(0, true);
                        Transponer(2, false);
                        break;
                    case "FilaArribaDerecha":
                        GirarIzquierda(0);
                        Transponer(4, false);
                        break;
                    case "FilaArribaIzquierda":
                        GirarDerecha(0);
                        Transponer(4, true);
                        break;
                    case "FilaAbajoDerecha":
                        GirarIzquierda(2);
                        Transponer(5, true);
                        break;
                    case "FilaVerticalDerechaArriba":
                        GirarAbajo(2);
                        Transponer(2, true);
                        break;
                    case "FilaVerticalIzquierdaAbajo":
                        GirarArriba(0);
                        Transponer(0, true);
                        break;
                    case "FilaVerticalDerechaAbajo":
                        GirarArriba(2);
                        Transponer(2, false);
                        break;
                    case "FilaAbajoIzquierda":
                        GirarDerecha(2);
                        Transponer(5, false);
                        break;
                    case "FilaVerticalIzquierda":
                        GirarAbajo(0);
                        Transponer(0, false);
                        break;
                }

                Platform.runLater(() -> {
                    PintarTodo();
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        resolverThread.start();
    }
}