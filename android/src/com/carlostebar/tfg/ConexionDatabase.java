package com.carlostebar.tfg;

//Instancio las clases que necesito
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionDatabase extends SQLiteOpenHelper {

    // Creo la base de datos de jugadores

    final String leatherBoard = "CREATE TABLE leatherboard (" +
            "  idPuntuacion INTEGER," +
            "  nombreJugador TEXT(5)," +
            "  puntuacion INTEGER," +
            "  personaje TEXT" +
            "); ";

    public ConexionDatabase(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(leatherBoard);

        db.execSQL("INSERT INTO leatherboard VALUES (1, 'Saul', 130, 'Stabbo');");
        db.execSQL("INSERT INTO leatherboard VALUES (2, 'Walt', 170, 'Stabbo');");
        db.execSQL("INSERT INTO leatherboard VALUES (3, 'Hank', 130, 'Stabbo');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
