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

        editText = findViewById(R.id.miTextfield);

        String nombreJugador = editText.getText().toString();

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrincipal.this, AndroidLauncher.class);
                startActivity(intent);
            }


        });
    }
}
