package com.carlostebar.tfg;

import android.os.Bundle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {

	public AndroidLauncher(){

	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		 BouncyStabbo appJuego=new BouncyStabbo();

		initialize((ApplicationListener) appJuego, config);
		System.out.println("--------------------------------"+appJuego.damePuntuacionImprimir());
	}
}
