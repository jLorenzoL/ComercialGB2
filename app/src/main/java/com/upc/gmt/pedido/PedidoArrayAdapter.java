package com.upc.gmt.pedido;

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

import java.util.List;

/**
 * Created by MALEX on 31/08/2017.
 */

public class PedidoArrayAdapter extends ArrayAdapter {

    private Context context;
    private List<ProductoBean> lista;
    private LayoutInflater inflater;

    public PedidoArrayAdapter(Context context, List<ProductoBean> lista){
        super(context, R.layout.calzado, lista);
        this.context = context;
        this.lista = lista;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.pedido, parent, false);
        }

            ProductoBean p = lista.get(position);

            TextView tvNombre = (TextView) convertView.findViewById(R.id.grid_pedido_label_nombre);
            tvNombre.setText(p.getNombre());

            TextView tvCodigo = (TextView) convertView.findViewById(R.id.grid_pedido_label_codigo);
            tvCodigo.setText(p.getCodigo());

            TextView tvColor = (TextView) convertView.findViewById(R.id.grid_pedido_label_color);
            tvColor.setText("Rojo");

            TextView tvTalla = (TextView) convertView.findViewById(R.id.grid_pedido_label_talla);
            tvTalla.setText("36");

            TextView tvPrecio = (TextView) convertView.findViewById(R.id.grid_pedido_label_precio);
            tvPrecio.setText(p.getPrecio());

            TextView tvTotalArticulo = (TextView) convertView.findViewById(R.id.tvTotalArticulo);
            tvTotalArticulo.setText("S/ "+p.getPrecio());


            ImageView imageView = (ImageView) convertView.findViewById(R.id.grid_pedido_image);

//            String calzado = "rojo";
//            if (calzado.equals("rojo")) {
                imageView.setImageResource(R.mipmap.calzado_rojo);
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
//                Picasso.with(context).load(Util.URL_WEB_SERVICE+"/verImagen?nombre="+p.getNombreImagen()).into(imageView);
            }catch (Exception e){
                e.printStackTrace();
                Log.e("ERROR", e.getMessage());
            }

        return convertView;
    }
}
