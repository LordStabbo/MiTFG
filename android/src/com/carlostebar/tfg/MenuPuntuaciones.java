package com.carlostebar.tfg;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
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


        String[] titulos = {"Nombre", "Puntuacion", "Personaje"};


        MiTableAdapter miAdapter = new MiTableAdapter(this, relleno);

        miVista.setHeaderAdapter(new SimpleTableHeaderAdapter(this, titulos));
        miVista.setDataAdapter(miAdapter);

    }
}
