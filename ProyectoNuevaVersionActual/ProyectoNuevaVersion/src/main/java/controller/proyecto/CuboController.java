/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.proyecto;

import static controller.proyecto.PrincipalController.Continuar;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

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
    final int CARAS = 6, FILAS = 3, COLUMNAS = 3;
    int i, j, k;
    int caras[] = {1, 4, 3, 5}; // Caras para arriba y abajo
    int ContadorMov = 0;
    boolean movRand = false;

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
    @FXML
    private Label lbl_mov;
    @FXML
    private Label lbl_cantmov;
    @FXML
    private Label lbl_tiempo;
    @FXML
    private Label lbl_cantTiempo;
    @FXML
    private Button btn_jugar;

    private Tiempo tiempo;
    @FXML
    private Button btn_GirarFilaArribaDerecha;
    @FXML
    private Button btn_GirarFIlaArribaIzquierda;
    @FXML
    private Button btn_GirarFilaAbajoDerecha;
    @FXML
    private Button btn_GirarFIlaAbajoIzquierda;
    @FXML
    private Button btn_GirarFilaVerticalIzquierda;
    @FXML
    private Button btn_GirarFIlaVerticalDerechaArriba;
    @FXML
    private Button btn_GirarFilaVerticalIzquierdaAbjo;
    @FXML
    private Button btn_GirarFilaVerticalDerechaAbajo;
    @FXML
    private Button btn_moverAbajo;
    @FXML
    private Button btn_moverArriba;
    @FXML
    private Button btn_moverDerecha;
    @FXML
    private Button btn_moverIzq;
    @FXML
    private Button btn_resolverSolo;
    @FXML
    private Button btn_aleatorio;
    
    boolean terminado = false;
    ScaleTransition animation = new ScaleTransition();
    ScaleTransition animation1 = new ScaleTransition();
    @FXML
    private GridPane grd_Cubo1;
    @FXML
    private Label lbl_ganador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Princip.Continuar) {
            movimientos = Accesib.leerMovimientosDesdeArchivo(Princip.nombre, movimientos);
            cubo.cubo = Accesib.leerMatrizDesdeArchivo(Princip.nombre);
            ContadorMov = Accesib.leerCantidadMovimientosDesdeArchivo(Princip.nombre);
            lbl_cantmov.setText(String.valueOf(Accesib.leerCantidadMovimientosDesdeArchivo(Princip.nombre)));
            tiempo = new Tiempo(Accesib.leerTiempoDesdeArchivo(Princip.nombre));
            if (tiempo != null) {
                lbl_cantTiempo.setText(tiempo.obtenerTiempoFormateado());
            }

            PintarTodo();
        } else {
            ReiniciarTodo();
        }

        Timeline actualizarTiempo = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (tiempo != null) {
                lbl_cantTiempo.setText(tiempo.obtenerTiempoFormateado());
            }
        }));
        actualizarTiempo.setCycleCount(Animation.INDEFINITE);
        actualizarTiempo.play();

    }

    public void ReiniciarTodo() { //Metodo para reiciar el juego
        if(terminado){
            habilitar();
        }else{
            btn_jugar.setDisable(false);
        }
        cubo.cubo = cubo.asignarValoresMatriz(cubo.cubo);
        PintarTodo();
        ContadorMov = 0;
        movRand = false;
        lbl_cantmov.setText("0");
        lbl_cantTiempo.setText("00:00:00");
        if (tiempo != null) {
            tiempo.reiniciarTiempo();
            tiempo = null;
        }
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
                double cellWidth = 130;
                double cellHeight = 130;
                rectangle.setWidth(cellWidth);
                rectangle.setHeight(cellHeight);

                // Borde negro
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
                double cellWidth = 30;
                double cellHeight = 30;
                rectangle.setWidth(cellWidth);
                rectangle.setHeight(cellHeight);

                // Borde negro
                rectangle.setStroke(Color.BLACK); // Color del borde
                rectangle.setStrokeWidth(2); // Grosor del borde
                rectangle.setStrokeType(StrokeType.INSIDE); // Tipo de trazo del borde (en el interior del rectángulo)

                gp.add(rectangle, col, row);
            }
        }
    }

    @FXML
    private void Volver(ActionEvent event) throws IOException { //Vuelve a la pantalla principal
        Princip.Continuar = false;
        App.setRoot("Principal");
    }

    @FXML
    private void moverAbajo(ActionEvent event) {
        MoverCaraAbajo();
        Transponer(0, true);
        Transponer(2, false);
        PintarTodo();
        movimientos.push("Abajo");
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
    }

    @FXML
    private void moverArriba(ActionEvent event) {
        MoverCaraArriba();
        Transponer(0, true);
        Transponer(2, false);
        PintarTodo();
        movimientos.push("Arriba");
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
    }

    @FXML
    private void moverDerecha(ActionEvent event) {
        MoverCaraDerecha();
        Transponer(4, false);
        Transponer(5, true);
        PintarTodo();
        movimientos.push("Derecha");
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
    }

    @FXML
    private void moverIzq(ActionEvent event) {
        MoverCaraIzquierda();
        Transponer(4, true);
        Transponer(5, false);
        PintarTodo();
        movimientos.push("Izquierda");
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
    }

    @FXML
    private void Guardar(ActionEvent event) {
        if (tiempo == null) {
            Accesib.guardarMatriz(cubo.cubo, Princip.nombre, movimientos, ContadorMov, "00:00:00");
        } else {
            tiempo.detenerTiempo();
            Accesib.guardarMatriz(cubo.cubo, Princip.nombre, movimientos, ContadorMov, tiempo.obtenerTiempoFormateado());
        }
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
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
    }

    @FXML
    private void GirarFIlaArribaIzquierda(ActionEvent event) {
        GirarIzquierda(0);
        Transponer(4, false);
        PintarTodo();
        movimientos.push("FilaArribaIzquierda");
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
    }

    @FXML
    private void GirarFilaAbajoDerecha(ActionEvent event) {
        GirarDerecha(2);
        Transponer(5, false);
        PintarTodo();
        movimientos.push("FilaAbajoDerecha");
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
    }

    @FXML
    private void GirarFIlaVerticalDerechaArriba(ActionEvent event) {
        GirarArriba(2);
        Transponer(2, false);
        PintarTodo();
        movimientos.push("FilaVerticalDerechaArriba");
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
    }

    @FXML
    private void GirarFilaVerticalIzquierdaAbjo(ActionEvent event) {
        GirarAbajo(0);
        Transponer(0, false);
        PintarTodo();
        movimientos.push("FilaVerticalIzquierdaAbajo");
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
    }

    @FXML
    private void GirarFilaVerticalDerechaAbajo(ActionEvent event) {
        GirarAbajo(2);
        Transponer(2, true);
        PintarTodo();
        movimientos.push("FilaVerticalDerechaAbajo");
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
    }

    @FXML
    private void GirarFIlaAbajoIzquierda(ActionEvent event) {
        GirarIzquierda(2);
        Transponer(5, true);
        PintarTodo();
        movimientos.push("FilaAbajoIzquierda");
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
    }

    @FXML
    private void GirarFilaVerticalIzquierda(ActionEvent event) {
        GirarArriba(0);
        Transponer(0, true);
        PintarTodo();
        movimientos.push("FilaVerticalIzquierda");
        if (movRand) {
            ContadorMov++;
            lbl_cantmov.setText(String.valueOf(ContadorMov));
            if (comprarCubo(cubo.getCubo().clone())) {
                terminado = true;
                tiempo.detenerTiempo();
                Accesib.guardarPuntuaciones(ContadorMov, tiempo.obtenerTiempoFormateado(), Princip.nombre);
                deshabilitar();
                animacion();
            }
        }
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
        tiempo.detenerTiempo();
    }

    @FXML
    private void Desarmar(ActionEvent event) {
        Random rand = new Random();
        int cantidadNumeros = 25;
        List<Integer> numerosAleatorios = new ArrayList<>();

        for (int i = 0; i < cantidadNumeros; i++) {
            int numeroAleatorio = rand.nextInt(12) + 1;
            numerosAleatorios.add(numeroAleatorio);

            switch (numerosAleatorios.get(i)) {
                case 1:
                    if (numeroAleatorio == 1) {
                        GirarFilaArribaDerecha(event);
                    }
                    break;
                case 2:
                    if (numeroAleatorio == 2) {
                        GirarFIlaArribaIzquierda(event);
                    }
                    break;
                case 3:
                    if (numeroAleatorio == 3) {
                        GirarFilaAbajoDerecha(event);
                    }
                    break;
                case 4:
                    if (numeroAleatorio == 4) {
                        GirarFIlaAbajoIzquierda(event);
                    }
                    break;
                case 5:
                    if (numeroAleatorio == 5) {
                        GirarFilaVerticalIzquierda(event);
                    }
                    break;
                case 6:
                    if (numeroAleatorio == 6) {
                        GirarFIlaVerticalDerechaArriba(event);
                    }
                    break;
                case 7:
                    if (numeroAleatorio == 7) {
                        GirarFilaVerticalIzquierdaAbjo(event);
                    }
                    break;
                case 8:
                    if (numeroAleatorio == 8) {
                        GirarFilaVerticalDerechaAbajo(event);
                    }
                    break;
                default:

            }
        }
        btn_jugar.setDisable(true);
        Jugar(event);
    }

    @FXML
    private void Jugar(ActionEvent event) {
        movRand = true;

        if (tiempo == null) {
            tiempo = new Tiempo();
        }

        tiempo.iniciarTiempo();
    }

    private boolean comprarCubo(int comparacion[][][]) {
        int cuboOrdenado[][][] = new int[6][3][3];
        cuboOrdenado = cubo.asignarValoresMatriz(cuboOrdenado);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (cuboOrdenado[i][j][k] != comparacion[i][j][k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void animacion() {
        animation.setNode(grd_Cubo1);
        animation.setDuration(Duration.millis(750));
        animation.setCycleCount(TranslateTransition.INDEFINITE);
        animation.setInterpolator(Interpolator.LINEAR);
        animation.setByX(0.3);
        animation.setByY(0.3);
        animation.setAutoReverse(true);
        
        animation1.setNode(lbl_ganador);
        animation1.setDuration(Duration.millis(750));
        animation1.setCycleCount(TranslateTransition.INDEFINITE);
        animation1.setInterpolator(Interpolator.LINEAR);
        animation1.setByX(0.3);
        animation1.setByY(0.3);
        animation1.setAutoReverse(true);
        
        animation.play();
        animation1.play();
    }

    private void deshabilitar() {
        grd_Cubo.setVisible(false);
        grd_Cubo1.setVisible(true);
        lbl_ganador.setVisible(true);
        grd_Cubo1.setStyle("-fx-background-color: red;");
        btn_jugar.setDisable(true);
        btn_GirarFilaArribaDerecha.setDisable(true);
        btn_GirarFIlaArribaIzquierda.setDisable(true);
        btn_GirarFilaAbajoDerecha.setDisable(true);
        btn_GirarFIlaAbajoIzquierda.setDisable(true);
        btn_GirarFilaVerticalIzquierda.setDisable(true);
        btn_GirarFIlaVerticalDerechaArriba.setDisable(true);
        btn_GirarFilaVerticalIzquierdaAbjo.setDisable(true);
        btn_GirarFilaVerticalDerechaAbajo.setDisable(true);
        btn_moverAbajo.setDisable(true);
        btn_moverArriba.setDisable(true);
        btn_moverDerecha.setDisable(true);
        btn_moverIzq.setDisable(true);
        btn_resolverSolo.setDisable(true);
        btn_aleatorio.setDisable(true);
    }
    
    private void habilitar() {
        animation.stop();
        animation1.stop();
        grd_Cubo.setVisible(true);
        grd_Cubo1.setVisible(false);
        lbl_ganador.setVisible(false);
        btn_jugar.setDisable(false);
        btn_GirarFilaArribaDerecha.setDisable(false);
        btn_GirarFIlaArribaIzquierda.setDisable(false);
        btn_GirarFilaAbajoDerecha.setDisable(false);
        btn_GirarFIlaAbajoIzquierda.setDisable(false);
        btn_GirarFilaVerticalIzquierda.setDisable(false);
        btn_GirarFIlaVerticalDerechaArriba.setDisable(false);
        btn_GirarFilaVerticalIzquierdaAbjo.setDisable(false);
        btn_GirarFilaVerticalDerechaAbajo.setDisable(false);
        btn_moverAbajo.setDisable(false);
        btn_moverArriba.setDisable(false);
        btn_moverDerecha.setDisable(false);
        btn_moverIzq.setDisable(false);
        btn_resolverSolo.setDisable(false);
        btn_aleatorio.setDisable(false);
        terminado = false;
    }
}
