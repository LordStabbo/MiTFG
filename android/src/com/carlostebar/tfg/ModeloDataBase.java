package com.carlostebar.tfg;

//Importo las librerias necesarias de SQL
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ModeloDataBase {

    public SQLiteDatabase dameConn(Context context) {

        ConexionDatabase miConexion = new ConexionDatabase(context, "LeatherBoardDatabase", null, 1);
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

}
