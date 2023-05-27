package com.carlostebar.tfg;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

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
		System.out.println("-------------Puntos salvados en la capa android-------------------"+puntos);
		Log.w("PUNTOS","-------------Puntos salvados en la capa android-------------------"+puntos);
		finish();
	}
}
