/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.proyecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javafx.util.Duration;

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

        if (archivo.exists()) {
            return true;
        }
        return false;
    }

    public void guardarMatriz(int valoresCubo[][][], String nombre, Stack movimientos, int cantmovimientos, String tiempo) { //Guarda la matriz en un txt
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

            bufferEscritura.write("Movimientos:");
            while (movimientos.empty() == false) {
                bufferEscritura.write(movimientos.pop() + ";");
            }

            bufferEscritura.write("\n");
            bufferEscritura.write("CantMovimientos: " + cantmovimientos);

            bufferEscritura.write("\n");
            bufferEscritura.write("Tiempo: " + tiempo);

            // Cerramos el archivo
            bufferEscritura.close();

        } catch (IOException e) {
            //En caso de error al escribir en el archivo, manejamos la excepción
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static BufferedReader abrirArchivo(String nombreJugador) throws IOException {
        String nombreArchivo = nombreJugador + ".txt";
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            throw new FileNotFoundException("El archivo no existe: " + nombreArchivo);
        }

        FileReader fileReader = new FileReader(archivo);
        return new BufferedReader(fileReader);

    }

    public static Stack<String> leerMovimientosDesdeArchivo(String nombreJugador, Stack<String> MOV) {
        List<String> movimientos = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = abrirArchivo(nombreJugador);

            String linea;
            boolean leyendoMovimientos = false;

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Movimientos:")) {
                    leyendoMovimientos = true;
                    // Agrega un salto de línea antes de empezar a leer los movimientos
                    System.out.println();
                    // Continúa al siguiente ciclo sin agregar la palabra "Movimientos:"
                    linea = linea.substring("Movimientos:".length());
                }

                if (leyendoMovimientos) {
                    String[] movs = linea.split(";");
                    for (String mov : movs) {
                        if (linea.startsWith("CantMovimientos:")) {
                            // Si la línea comienza con "CantMovimientos:", detén la lectura de movimientos
                            break;
                        } else {
                            movimientos.add(mov.trim());
                        }

                    }
                }
            }

            for (int i = movimientos.size() - 1; i >= 0; i--) {
                MOV.add(movimientos.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return MOV;
    }

    public static int[][][] leerMatrizDesdeArchivo(String nombreJugador) {
        int[][][] matrices = new int[6][3][3];
        BufferedReader br = null;

        try {
            br = abrirArchivo(nombreJugador);

            String linea;
            int matrizActual = -1;
            int fila = 0;

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Izquierda") || linea.startsWith("Frente") || linea.startsWith("Derecha")
                        || linea.startsWith("Atras") || linea.startsWith("Arriba") || linea.startsWith("Abajo")) {
                    // Detectar el tipo de matriz y configurar el índice adecuado
                    if (linea.startsWith("Izquierda")) {
                        matrizActual = 0;
                    } else if (linea.startsWith("Frente")) {
                        matrizActual = 1;
                    } else if (linea.startsWith("Derecha")) {
                        matrizActual = 2;
                    } else if (linea.startsWith("Atras")) {
                        matrizActual = 3;
                    } else if (linea.startsWith("Arriba")) {
                        matrizActual = 4;
                    } else if (linea.startsWith("Abajo")) {
                        matrizActual = 5;
                    }

                    fila = 0;
                } else if (matrizActual != -1 && fila < 3) {
                    // Leer números de la matriz y llenar la matriz tridimensional
                    String[] valores = linea.split("\\|");
                    for (int columna = 1; columna < valores.length; columna++) {
                        matrices[matrizActual][columna - 1][fila] = Integer.parseInt(valores[columna].trim());
                    }
                    fila++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return matrices;
    }

    public static int leerCantidadMovimientosDesdeArchivo(String nombreJugador) {
        BufferedReader br = null;
        int cantidadMovimientos = 0; // Variable para almacenar la cantidad de movimientos

        try {
            br = abrirArchivo(nombreJugador);

            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("CantMovimientos:")) {
                    cantidadMovimientos = Integer.parseInt(linea.substring("CantMovimientos:".length()).trim());
                    break; // Detén la lectura después de encontrar la cantidad de movimientos
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return cantidadMovimientos;
    }

    public Duration leerTiempoDesdeArchivo(String nombreJugador) {
        BufferedReader br = null;
        Duration tiempoD = null; // Variable para almacenar la cantidad de tiempo
        String tiempo = null;

        try {
            br = abrirArchivo(nombreJugador);

            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Tiempo:")) {
                    tiempo = (linea.substring("Tiempo:".length()).trim());
                    break; // Detiene la lectura después de encontrar la cantidad de tiempo
                }
            }

            String[] partes = tiempo.split(":");
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            int segundos = Integer.parseInt(partes[2]);

            // Calcula la duración total en milisegundos
            long duracionMilisegundos = (horas * 3600 + minutos * 60 + segundos) * 1000;

            tiempoD = Duration.millis(duracionMilisegundos);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tiempoD;
    }

    public void guardarPuntuaciones(int movimientos, String tiempo, String nombreJugador) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("MejoresPuntuaciones.txt", true))) {
            String puntuacion = nombreJugador + ";" + tiempo + ";" + movimientos;
            writer.write(puntuacion);
            writer.newLine(); 
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static List<MejoresPuntuaciones> leerMejoresPuntuacionesDesdeArchivo() {
        List<MejoresPuntuaciones> puntuaciones = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("MejoresPuntuaciones.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String tiempo = partes[1].trim();
                    int movimientos = Integer.parseInt(partes[2].trim());

                    MejoresPuntuaciones puntuacion = new MejoresPuntuaciones(nombre, tiempo, movimientos);
                    puntuaciones.add(puntuacion);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return puntuaciones;
    }

}
