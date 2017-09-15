package com.upc.gmt.catalogo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.upc.gmt.bean.ProductoBean;
import com.upc.gmt.comercialgb.R;
import com.upc.gmt.util.Util;

import java.util.List;

/**
 * Created by MALEX on 31/08/2017.
 */

public class ImagenCalzadoArrayAdapter extends ArrayAdapter {

    private Context context;
    private List<ProductoBean> lista;
    private LayoutInflater inflater;

    public ImagenCalzadoArrayAdapter(Context context, List<ProductoBean> lista){
        super(context, R.layout.calzado, lista);
        this.context = context;
        this.lista = lista;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        View gridView;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.calzado, parent, false);
        }
//            gridView = new View(context);

            // get layout from mobile.xml
//            gridView = inflater.inflate(R.layout.calzado, null);

            ProductoBean p = lista.get(position);

            // set value into textview
            TextView tvNombre = (TextView) convertView.findViewById(R.id.grid_item_label_nombre);
            tvNombre.setText(p.getNombre());

            TextView tvCodigo = (TextView) convertView.findViewById(R.id.grid_item_label_codigo);
            tvCodigo.setText(p.getCodigo());

            TextView tvPrecio = (TextView) convertView.findViewById(R.id.grid_item_label_precio);
            tvPrecio.setText(p.getPrecio());

            // set image based on selected text
            ImageView imageView = (ImageView) convertView.findViewById(R.id.grid_item_image);

//            String calzado = "rojo";
//            if (calzado.equals("rojo")) {
//                imageView.setImageResource(R.mipmap.calzado_rojo);
//            } else if (calzado.equals("amarillo")) {
//                imageView.setImageResource(R.mipmap.calzado_amarillo);
//            } else if (calzado.equals("verde")) {
//                imageView.setImageResource(R.mipmap.calzado_verde);
//            } else {
//                imageView.setImageResource(R.mipmap.calzado_rojo);
//            }
            Picasso picasso = Picasso.with(context);
            picasso.setIndicatorsEnabled(true);
            picasso.setLoggingEnabled(true);
            try {
                Picasso.with(context).load(Util.URL_WEB_SERVICE+"/imagen?nombre="+p.getNombreImagen()).into(imageView);
            }catch (Exception e){
                e.printStackTrace();
                Log.e("ERROR", e.getMessage());
            }

        return convertView;
    }
}
