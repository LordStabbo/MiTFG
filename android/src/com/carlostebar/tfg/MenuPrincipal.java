package com.carlostebar.tfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.textfield.TextInputEditText;

public class MenuPrincipal extends AppCompatActivity {

    private TextInputEditText editText;
    private VideoView videoView;

     String nombreJugador;

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        videoView.setVisibility(View.GONE);
    }


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

        //Creo Un videoView  para hacer la animacion de entrada
        videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.intro;;
        videoView.setVideoURI(Uri.parse(videoPath));



        videoView.start();
        videoView.setOnCompletionListener(mp -> {
            // Video playback has finished
            videoView.setVisibility(View.GONE);

        });

        editText = findViewById(R.id.miTextfield);

        editText.setTypeface(typeface);
        editText.setTextSize(30);
        botonInsertar.setTypeface(typeface);
        botonLeather.setTypeface(typeface);
        botonInsertar.setTextSize(24);
        botonLeather.setTextSize(24);

        nombreJugador = editText.getText().toString();


        botonLeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoView.isPlaying()){
                    Toast.makeText(MenuPrincipal    .this, "Loading Database", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MenuPrincipal.this,MenuPuntuaciones.class);
                    startActivity(intent);
                }



            }
        });

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreJugador = String.valueOf(editText.getText());
                if(nombreJugador.isEmpty()){
                    Toast.makeText(MenuPrincipal.this, "Introduzca un nombre de Jugador", Toast.LENGTH_SHORT).show();

                }else{
                    /*Creo unas SharedPreferences para "enviar" el nombre que el jugador ha introducido
                    * a la actividad en la que se inserta en la BD junto a la puntuacion que este
                    * jugador consiga*/
                    SharedPreferences preferences = getSharedPreferences("MisPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("nombre_jugador", nombreJugador);
                    editor.apply();


                    Intent intent = new Intent(MenuPrincipal.this,AndroidLauncher.class);
                    startActivity(intent);
                }
            }


        });





    }
}
