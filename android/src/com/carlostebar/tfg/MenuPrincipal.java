package com.carlostebar.tfg;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.textfield.TextInputEditText;

public class MenuPrincipal extends AppCompatActivity {

    private TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Con esto importo la letra estilo Pixel de la app
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fuentes/silkscreen.ttf");

        //Impido que la actividad tenga modo oscuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.menu_principal);

        //Instancio los botones que lanzan el juego y las leatherboards, asi como el editext del nombre
        Button botonInsertar = findViewById(R.id.botonJugar);
        Button botonLeather = findViewById(R.id.botonLeatherBoard);

        editText = findViewById(R.id.miTextfield);

        editText.setTypeface(typeface);
        editText.setTextSize(30);
        botonInsertar.setTypeface(typeface);
        botonLeather.setTypeface(typeface);
        botonInsertar.setTextSize(24);
        botonLeather.setTextSize(24);

        String nombreJugador = editText.getText().toString();


        botonLeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent intent = new Intent(MenuPrincipal.this,MenuPuntuaciones.class);
                startActivity(intent);
            }
        });

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, AndroidLauncher.class);
                startActivity(intent);
            }


        });


    }
}
