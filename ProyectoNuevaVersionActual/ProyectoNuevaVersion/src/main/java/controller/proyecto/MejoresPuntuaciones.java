/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.proyecto;

/**
 *
 * @author joxan
 */
public class MejoresPuntuaciones {
    String Nombre;
    String Tiempo;
    int Movimientos;

    public MejoresPuntuaciones(String Nombre, String Tiempo, int Movimientos) {
        this.Nombre = Nombre;
        this.Tiempo = Tiempo;
        this.Movimientos = Movimientos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String Tiempo) {
        this.Tiempo = Tiempo;
    }

    public int getMovimientos() {
        return Movimientos;
    }

    public void setMovimientos(int Movimientos) {
        this.Movimientos = Movimientos;
    }
    
    
}
