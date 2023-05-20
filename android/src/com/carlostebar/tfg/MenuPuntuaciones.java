package com.carlostebar.tfg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class MenuPuntuaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_puntuaciones);


        ModeloDataBase miModelo = new ModeloDataBase();



        TableView miVista = findViewById(R.id.tablaPuntuaciones);
        String[] titulos = {"Nombre", "Puntuacion", "Personaje"};
        String[][] relleno ={{"Saul", "120", "Stabbo"},{"Saul", "120", "Stabbo"},{"Saul", "120", "Stabbo"}};

        miVista.setHeaderAdapter(new SimpleTableHeaderAdapter(this, titulos));
        miVista.setDataAdapter(new SimpleTableDataAdapter(this, relleno));

    }
}
