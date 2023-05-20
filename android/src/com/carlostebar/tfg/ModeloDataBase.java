package com.carlostebar.tfg;

//Importo las librerias necesarias de SQL

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    String[] dameTuplas(Context context){
        DatabaseController miController = new DatabaseController(context);
        SQLiteDatabase sqld = miController.getWritableDatabase();

        String nombreTabla = "leatherboard";
        String [] columnas = {"idPuntuacion", "nombreJugador", "puntuacion", "personaje"};
        String orden = "puntuacion ASC";

        Cursor cursor =  sqld.query(nombreTabla, columnas, null,null, null, null,orden);

        while(cursor.moveToNext()){

           /*
           *
           *
           *
           *
           *
           *  String nombreJugador = cursor.getString(cursor.getColumnIndex("nombreJugador"));
           */


        }


        return null;
    }

}
