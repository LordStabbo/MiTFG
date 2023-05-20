package com.carlostebar.tfg;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class MenuPuntuaciones extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Impido que la actividad tenga modo oscuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.menu_puntuaciones);

        //Hago el boton Back
        Button botonAtras = findViewById(R.id.botonBack);

        //Instancio el modelo de mi base de datos, despues a partir de el uso el metodo que devuelve
        //un arraylist de Stringns, que despues convierto en un Array bidimensional de Strings, que es
        //lo que pide el TableView
        ModeloDataBase miModelo = new ModeloDataBase();
        List <String[]> misTuplas = miModelo.dameTuplas(MenuPuntuaciones.this);
        String[][] relleno =new String[misTuplas.size()][];

        for(int i = 0; i < misTuplas.size(); i++){
            relleno[i] = misTuplas.get(i);
        }

        TableView miVista = findViewById(R.id.tablaPuntuaciones);


        String[] titulos = {"Pos","Name", "Pts", "Char"};


        MiTableAdapter miAdapter = new MiTableAdapter(this, relleno);
        MiTableHeaderAdapter miHeaderAdapter = new MiTableHeaderAdapter(this, titulos);

        miVista.setHeaderAdapter(miHeaderAdapter);
        miVista.setDataAdapter(miAdapter);

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuPuntuaciones.this,MenuPrincipal.class);
                startActivity(intent);
            }
        });

    }
}
