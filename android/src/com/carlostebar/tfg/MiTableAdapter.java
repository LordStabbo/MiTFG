package com.carlostebar.tfg;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

// Esta clase hace un TableDataAdapter que me servira para pintar la tabla de puntuaciones de manera
//personalizada
public class MiTableAdapter extends TableDataAdapter<String[]> {

    //Con esto importo la letra estilo Pixel de la app
    Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fuentes/silkscreen.ttf");
    public MiTableAdapter(Context context, String[][] data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        // Obtengo la info de cada columna y fila
        String[] rowData = getRowData(rowIndex);
        String cellData = rowData[columnIndex];

        // Creo un TextView para cada fila
        TextView textView = new TextView(getContext());
        textView.setText(cellData);

        // y las personalizo
        if (rowIndex % 2 == 0) {
            // Las filas pares de un color
            textView.setTypeface(typeface);
            textView.setTextColor(Color.parseColor("#05DBF2"));
            textView.setTextSize(22);
        } else {
            // y las impares de otro
            textView.setTypeface(typeface);
            textView.setTextColor(Color.parseColor(
                    "#05DBF2"));
            textView.setTextSize(22);
        }


        return textView;
    }



}