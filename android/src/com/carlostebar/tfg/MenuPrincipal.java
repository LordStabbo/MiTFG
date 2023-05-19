package com.carlostebar.tfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MenuPrincipal extends AppCompatActivity {

    private TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        Button botonInsertar = findViewById(R.id.botonJugar);
        Button botonLeather = findViewById(R.id.botonLeatherBoard);

        editText = findViewById(R.id.miTextfield);

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
