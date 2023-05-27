package com.carlostebar.tfg;

//Instancio las clases que necesito
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseController extends SQLiteOpenHelper {

    // Creo la base de datos de jugadores

    final String leatherBoard = "CREATE TABLE leatherboard (" +
            "  idPuntuacion INTEGER," +
            "  nombreJugador TEXT(5)," +
            "  puntuacion INTEGER," +
            "  personaje TEXT" +
            "); ";

    public DatabaseController(Context context) {
        super(context, "LeatherBoardDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(leatherBoard);

        db.execSQL("INSERT INTO leatherboard VALUES (1, 'Saul', 13, 'Stabbo');");
        db.execSQL("INSERT INTO leatherboard VALUES (2, 'Walt', 17, 'Stabbo');");
        db.execSQL("INSERT INTO leatherboard VALUES (3, 'Hank', 13, 'Stabbo');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
