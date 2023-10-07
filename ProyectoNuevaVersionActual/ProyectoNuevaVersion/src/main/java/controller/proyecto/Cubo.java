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

    int cubo[][][] = new int[6][3][3];

    public Cubo() {
    }

    public int[][][] getCubo() {
        return cubo;
    }

    public void setCubo(int[][][] cubo) {
        this.cubo = cubo;
    }

    public int[][][] asignarValoresMatriz(int rellenar[][][]) { //Rellena la matriz con numeros del 1 al 6 para asignar colores.
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    switch (i) {
                        case 0:
                            rellenar[i][k][j] = 1;
                            break;
                        case 1:
                            rellenar[i][k][j] = 2;
                            break;
                        case 2:
                            rellenar[i][k][j] = 3;
                            break;
                        case 3:
                            rellenar[i][k][j] = 4;
                            break;
                        case 4:
                            rellenar[i][k][j] = 5;
                            break;
                        case 5:
                            rellenar[i][k][j] = 6;
                            break;
                    }
                }
            }
        }
        return rellenar;
    }
}
