/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.proyecto;

//Universidad Nacional, Campus Coto
//Desarrollado por:
//Joxan Portilla Hernandez
//Melani Barrantes Hidalgo
//Alberto Torres
//Kimberly Porras
//2023
public class Cubo {

    PrincipalController Princip = new PrincipalController();
    Accesibilidad Accesib = new Accesibilidad();

    int cubo[][][] = new int[6][3][3];
    int aux[][] = new int[3][3];

    public Cubo() {
    }

    public int[][][] getCubo() {
        return cubo;
    }

    public void setCubo(int[][][] cubo) {
        this.cubo = cubo;
    }

    public int[][] getAux() {
        return aux;
    }

    public void setAux(int[][] aux) {
        this.aux = aux;
    }

    @Override
    public String toString() {
        return "Cubo{" + "cubo=" + cubo + ", aux=" + aux + '}';
    }

    public void asignarValoresMatriz() { //Rellena la matriz con numeros del 1 al 6 para asignar colores.
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    switch (i) {
                        case 0:
                            cubo[i][k][j] = 1;
                            break;
                        case 1:
                            cubo[i][k][j] = 2;
                            aux[j][k] = 2;
                            break;
                        case 2:
                            cubo[i][k][j] = 3;
                            break;
                        case 3:
                            cubo[i][k][j] = 4;
                            break;
                        case 4:
                            cubo[i][k][j] = 5;
                            break;
                        case 5:
                            cubo[i][k][j] = 6;
                            break;
                    }
                }
            }
        }
        //Accesib.guardarMatriz(cubo, Princip.nombre);
    }

    public void moverAbajo() {
        for (int i = 0; i < 3; i++) { //Cara presentable pasarla a aux para mover matriz
            for (int j = 0; j < 3; j++) {
                aux[j][i] = cubo[1][j][i];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[1][k][j] = cubo[4][k][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[4][k][j] = cubo[3][k][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[3][k][j] = cubo[5][k][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[5][k][j] = aux[k][j];
            }
        }

        trasponerMatriz(0, 0);
        trasponerMatriz(2, 1);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo[i][k][j]);
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
    }

    public void moverArriba() {

        for (int i = 0; i < 3; i++) { //Cara presentable pasarla a aux para mover matriz
            for (int j = 0; j < 3; j++) {
                aux[j][i] = cubo[1][j][i];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[1][k][j] = cubo[5][k][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[5][k][j] = cubo[3][k][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[3][k][j] = cubo[4][k][j];
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cubo[4][j][k] = aux[j][k];
            }
        }
        trasponerMatriz(0, 0);
        trasponerMatriz(2, 1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo[i][k][j]);
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
    }

    public void moverDerecha() {
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

        trasponerMatriz(4, 1);
        trasponerMatriz(5, 0);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo[i][k][j]);
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
    }

    public void moverIzq() {
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
        trasponerMatriz(4, 0);
        trasponerMatriz(5, 1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + cubo[i][k][j]);
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
    }

    public static int[][] transponerDerecha(int[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int[][] matrizTranspuesta = new int[columnas][filas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizTranspuesta[j][i] = matriz[filas - 1 - i][j];
            }
        }
        return matrizTranspuesta;
    }

    public static int[][] transponerIzquierda(int[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int[][] matrizTranspuesta1 = new int[columnas][filas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizTranspuesta1[j][i] = matriz[i][columnas - 1 - j];
            }
        }
        return matrizTranspuesta1;
    }

    public void trasponerMatriz(int cara, int direccion) {
        int[][] matrizAux = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizAux[j][i] = cubo[cara][j][i];
            }
        }
        if (direccion == 0) {
            matrizAux = transponerDerecha(matrizAux);
        } else {
            matrizAux = transponerIzquierda(matrizAux);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cubo[cara][i][j] = matrizAux[i][j];
            }
        }
    }

    public void imprimirMatriz(int mt[][][]) {
        System.out.println("\n");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print("| " + mt[i][k][j]);
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
    }
}
