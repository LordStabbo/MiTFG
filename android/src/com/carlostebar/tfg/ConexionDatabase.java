package com.carlostebar.tfg;

//Instancio las clases que necesito
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionDatabase extends SQLiteOpenHelper {

    // Creo la base de datos de jugadores

    final String leatherBoard = "CREATE TABLE leatherboard (" +
            "  idPuntuacion INTEGER," +
            "  nombreJugador TEXT," +
            "  puntuacion INTEGER," +
            "  personaje TEXT" +
            "); " +
            "INSERT INTO leatherboard VALUES" +
            "(1, 'Saul', 130, 'Stabbo')," +
            "(2, 'Walt', 170, 'Stabbo')," +
            "(3, 'Hank', 130, 'Stabbo');";

    public ConexionDatabase(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(leatherBoard);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
