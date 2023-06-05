package com.carlostebar.tfg;

//Importo las librerias necesarias de SQL

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.carlostebar.tfg.database.DatabaseController;
import com.carlostebar.tfg.database.PojoPuntuaciones;

import java.util.ArrayList;
import java.util.List;

public class ModeloDataBase {

    //Creo la conexion con la Base de datos
    public SQLiteDatabase dameConn(Context context) {

        DatabaseController miConexion = new DatabaseController(context);
        SQLiteDatabase miDataBase = miConexion.getWritableDatabase();
        return miDataBase;
    }

    //Este metodo lo uso para obtener el contenido de la leaderboard
    int consultaPuntuaciones(Context context) {


        int ronda = 0;
        String consultaPuntuaciones = "SELECT * FROM leatherboard ORDER BY puntuacion DESC;";

        SQLiteDatabase sqldb = this.dameConn(context);

        try {
            ronda = 1;
            sqldb.execSQL(consultaPuntuaciones);
        } catch (Exception e) {

        }
        return ronda;
    }

    //Esta consulta obtiene todas las puntuaciones ordenadas de mayor a menor por su valor
    List<String[]> dameTuplas(Context context){
        DatabaseController miController = new DatabaseController(context);
        SQLiteDatabase sqld = miController.getWritableDatabase();

        String nombreTabla = "leatherboard";
        String [] columnas = {"idPuntuacion", "nombreJugador", "puntuacion", "personaje"};
        String orden = "puntuacion DESC";

        Cursor cursor =  sqld.query(nombreTabla, columnas, null,null, null, null,orden);

        List<String[]> tuplas = new ArrayList<>();
        int posicion = 0;
        try {
            if (cursor != null) {
                while(cursor.moveToNext()){
                    posicion ++;
                    int idPuntuacion = cursor.getInt(cursor.getColumnIndexOrThrow("idPuntuacion"));
                    String nombreJugador = cursor.getString(cursor.getColumnIndexOrThrow("nombreJugador"));
                    int puntuacion = cursor.getInt(cursor.getColumnIndexOrThrow("puntuacion"));
                    String personaje = cursor.getString(cursor.getColumnIndexOrThrow("personaje"));

                    String [] tupla = {String.valueOf(posicion),nombreJugador, String.valueOf(puntuacion),personaje};
                    tuplas.add(tupla);

                }

            }
        }
        finally {
            if(cursor != null) {
                cursor.close();
            }
        }

        return tuplas;
    }

    //Con este metodo guardo las puntuaciones en la BD al salir de partida
    int insertaPuntos(Context context, PojoPuntuaciones misPuntos){
        int vuelta = 0;

        String insertaPuntos ="INSERT INTO leatherboard (idPuntuacion, nombreJugador, puntuacion, personaje)" +
                "VALUES ("+misPuntos.getId()+", '"+misPuntos.getNombre().trim()+"', "+misPuntos.getPuntuacion()+" , '"+misPuntos.getPersonaje().trim()+"' );";

        SQLiteDatabase sqldb = this.dameConn(context);

        try{
            vuelta = 1;
            sqldb.execSQL(insertaPuntos);
        }catch (SQLException e){}

        return vuelta;
    }



}
