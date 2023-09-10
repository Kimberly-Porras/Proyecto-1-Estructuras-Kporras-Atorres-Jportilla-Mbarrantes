/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Accesibilidad {

    public static boolean verificarNombreJugador(String nombreJugador) {
        // Ruta completa al directorio
        String directorio = "C:\\Users\\joxan\\OneDrive\\Documentos\\GitHub\\Proyecto-1-Estructuras-Kporras-Atorres-Jportilla-Mbarrantes\\ProyectoNuevaVersion\\";

        // Verificar si el archivo con el mismo nombre ya existe
        File archivo = new File(directorio + nombreJugador + ".txt");
        return archivo.exists();
    }

    public static void generarArchivoTexto(String nombreJugador) throws IOException {
        // Ruta completa al directorio
        String directorio = "C:\\Users\\joxan\\OneDrive\\Documentos\\GitHub\\Proyecto-1-Estructuras-Kporras-Atorres-Jportilla-Mbarrantes\\ProyectoNuevaVersion\\";

        // Verificar si el archivo con el mismo nombre ya existe
        if (verificarNombreJugador(nombreJugador)) {
            throw new IOException("El archivo " + nombreJugador + ".txt" + " ya existe en el directorio.");
        }

        /*try {
            // Crear el archivo en el directorio
            File nuevoArchivo = new File(directorio + nombreJugador + ".txt");
            nuevoArchivo.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar cualquier error de escritura en el archivo aquí
            throw e;
        }*/
    }


        

    public void guardarMatriz(int valoresCubo[][][], String nombre) { //Guarda la matriz en un txt
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
                        bufferEscritura.write("| " + valoresCubo[i][j][k]);
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
}
