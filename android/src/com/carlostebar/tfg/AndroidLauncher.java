package com.carlostebar.tfg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.carlostebar.tfg.database.PojoPuntuaciones;

public class AndroidLauncher extends AndroidApplication implements Puente {

	public AndroidLauncher(){

	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		 BouncyStabbo appJuego=new BouncyStabbo();
		 appJuego.tomaPuenteCore(this);

		initialize((ApplicationListener) appJuego, config);

	}

	@Override
	public void enviaPuntos(int puntos) {

		/*Con la clase SharedPreferences recupero el nombre que el jugador ha introducido en el menu
		* principal*/
		SharedPreferences preferences = getSharedPreferences("MisPrefs", Context.MODE_PRIVATE);
		String nombreJugador = preferences.getString("nombre_jugador", "Player");
		System.out.println("-------------Puntos salvados en la capa android-------------------"+puntos);
		Log.w("PUNTOS","-------------Puntos salvados en la capa android-------------------"+puntos);
		Log.w("NOMBRE","-------------Nombre salvados en la capa android-------------------"+nombreJugador);

		/*Instancio mi modelo de database para hacer la insercion de la nueva puntuacion*/
		ModeloDataBase miModelo = new ModeloDataBase();

		/*e intento insertar un pojoPuntuaciones*/
		PojoPuntuaciones misPuntos = new PojoPuntuaciones(1, nombreJugador, puntos, "stabbo");

		miModelo.insertaPuntos(AndroidLauncher.this, misPuntos);



		finish();
	}
}
