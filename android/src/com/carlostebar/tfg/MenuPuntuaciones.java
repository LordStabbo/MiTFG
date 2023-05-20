package com.carlostebar.tfg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class MenuPuntuaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_puntuaciones);


        ModeloDataBase miModelo = new ModeloDataBase();
        List <String[]> misTuplas = miModelo.dameTuplas(MenuPuntuaciones.this);
        String[][] relleno =new String[misTuplas.size()][];

        for(int i = 0; i < misTuplas.size(); i++){
            relleno[i] = misTuplas.get(i);
        }

        TableView miVista = findViewById(R.id.tablaPuntuaciones);
        String[] titulos = {"Nombre", "Puntuacion", "Personaje"};


        miVista.setHeaderAdapter(new SimpleTableHeaderAdapter(this, titulos));
        miVista.setDataAdapter(new SimpleTableDataAdapter(this, relleno));

    }
}
