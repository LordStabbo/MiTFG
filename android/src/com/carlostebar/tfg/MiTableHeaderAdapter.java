package com.carlostebar.tfg;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.codecrafters.tableview.TableHeaderAdapter;


public class MiTableHeaderAdapter extends TableHeaderAdapter {

    private Typeface typeface;
    private String[] titulos;
    private Context context;

    //Esta clase crea un modelo de TableHeaderAdapter customizado para mi leatherboard
    public MiTableHeaderAdapter(Context context, String... titulos) {
        super(context);
        this.context = context;
        this.titulos=titulos;
        typeface = Typeface.createFromAsset(getContext().getAssets(), "fuentes/silkscreen.ttf");
    }

    @Override
    public View getHeaderView(int columnIndex, ViewGroup parentView) {
        TextView textView = new TextView(context);
        textView.setText(titulos[columnIndex]);

        // Aplico mi letra personalizada
        textView.setTypeface(typeface);

        // Cambio las propiedades del Header
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
        textView.setTypeface(typeface);
        textView.setTextColor(Color.parseColor("#ff00ff"));
        textView.setBackgroundColor(Color.parseColor("#000228"));

        return textView;
    }
}
