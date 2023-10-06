/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

//Universidad Nacional, Campus Coto
//Desarrollado por:
    //Joxan Portilla Hernandez
    //Melani Barrantes Hidalgo
    //Alberto Torres
    //Kimberly Porras
//2023

public class Accesibilidad {

    public static boolean verificarNombreJugador(String nombreJugador) throws FileNotFoundException {
        File archivo = new File(nombreJugador + ".txt");
        
        if(archivo.exists()){
            return true;
        }
        return false;
    }
    
    public void guardarMatriz(int valoresCubo[][][], String nombre, Stack movimientos) { //Guarda la matriz en un txt
        // Nombre del archivo que vamos a crear. Si no exite lo crea y si existe escribe
        String nombreArchivo = nombre + ".txt";

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
                        bufferEscritura.write("| " + valoresCubo[i][k][j]);
                    }
                    bufferEscritura.write("\n");
                }
                bufferEscritura.write("\n");
            }
            
            while(movimientos.empty() == false){
                bufferEscritura.write(movimientos.pop() + ";");
            }

            // Cerramos el archivo
            bufferEscritura.close();

        } catch (IOException e) {
            //En caso de error al escribir en el archivo, manejamos la excepción
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
