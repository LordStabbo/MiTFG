package com.carlostebar.tfg;

//Importo las librerias necesarias de SQL

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ModeloDataBase {

    public SQLiteDatabase dameConn(Context context) {

        DatabaseController miConexion = new DatabaseController(context);
        SQLiteDatabase miDataBase = miConexion.getWritableDatabase();
        return miDataBase;
    }

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

    List<String[]> dameTuplas(Context context){
        DatabaseController miController = new DatabaseController(context);
        SQLiteDatabase sqld = miController.getWritableDatabase();

        String nombreTabla = "leatherboard";
        String [] columnas = {"idPuntuacion", "nombreJugador", "puntuacion", "personaje"};
        String orden = "puntuacion DESC";

        Cursor cursor =  sqld.query(nombreTabla, columnas, null,null, null, null,orden);

        List<String[]> tuplas = new ArrayList<>();

        try {
            if (cursor != null) {
                while(cursor.moveToNext()){

                    int idPuntuacion = cursor.getInt(cursor.getColumnIndexOrThrow("idPuntuacion"));
                    String nombreJugador = cursor.getString(cursor.getColumnIndexOrThrow("nombreJugador"));
                    int puntuacion = cursor.getInt(cursor.getColumnIndexOrThrow("puntuacion"));
                    String personaje = cursor.getString(cursor.getColumnIndexOrThrow("personaje"));

                    String [] tupla = {String.valueOf(idPuntuacion), nombreJugador, String.valueOf(puntuacion), personaje};
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



}
