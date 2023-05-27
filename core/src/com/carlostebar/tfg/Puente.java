package com.carlostebar.tfg;

public interface Puente {

    /*Este interfaz es el nexo de comunicación entre las dos capas del juego (core y android) que nos
    * permitira "enviar" las puntuaciones que se van generando en el juego para guardarlas en las
    * BBDD de la capa android*/

    void enviaPuntos(int puntos);
}
