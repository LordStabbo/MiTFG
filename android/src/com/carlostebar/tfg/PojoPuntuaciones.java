package com.carlostebar.tfg;

public class PojoPuntuaciones {

    private int id;
    private String nombre;
    private int puntuacion;
    private String nombrePersonaje;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getPersonaje() {
        return nombrePersonaje;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setNombrePersonaje(String nombrePersonaje) {
        this.nombrePersonaje = nombrePersonaje;
    }

}
