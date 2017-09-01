package com.upc.gmt.comercialgb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by MALEX on 31/08/2017.
 */

public class ImagenCalzadoAdapter extends BaseAdapter {

    private Context context;
    private final String[] calzadoValues;

    public ImagenCalzadoAdapter(Context context, String[] calzadoValues){
        this.context = context;
        this.calzadoValues = calzadoValues;
    }

    @Override
    public int getCount() {
        return calzadoValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.calzado, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(calzadoValues[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String calzado = calzadoValues[position];

            if (calzado.equals("rojo")) {
                imageView.setImageResource(R.mipmap.calzado_rojo);
            } else if (calzado.equals("amarillo")) {
                imageView.setImageResource(R.mipmap.calzado_amarillo);
            } else if (calzado.equals("verde")) {
                imageView.setImageResource(R.mipmap.calzado_verde);
            } else {
                imageView.setImageResource(R.mipmap.calzado_rojo);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
