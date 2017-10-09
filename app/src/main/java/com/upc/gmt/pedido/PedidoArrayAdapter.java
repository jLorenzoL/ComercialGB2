package com.upc.gmt.pedido;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.upc.gmt.comercialgb.R;
import com.upc.gmt.model.Producto;
import com.upc.gmt.util.Util;

import java.util.List;

import static com.upc.gmt.comercialgb.R.layout.calzado;

/**
 * Created by MALEX on 31/08/2017.
 */

public class PedidoArrayAdapter extends ArrayAdapter {

    private Context context;
    private List<Producto> lista;
    private LayoutInflater inflater;

    public PedidoArrayAdapter(Context context, List<Producto> lista){
        super(context, calzado, lista);
        this.context = context;
        this.lista = lista;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.pedido, parent, false);
        }

            CheckBox chbArticulo = (CheckBox) convertView.findViewById(R.id.chbArticulo);
            chbArticulo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    double totalPrecio = 0.00;
                    ListView lvPedidos = (ListView) buttonView.getRootView().findViewById(R.id.lvPedidos);
                    for (int i = 0; i < lvPedidos.getCount(); i++){
                        View viewPedido = Util.getViewByPosition(i, lvPedidos);
                        CheckBox chbArticulo = (CheckBox) viewPedido.findViewById(R.id.chbArticulo);
                        if(chbArticulo.isChecked()){
                            EditText txtCantidadPedido = (EditText) viewPedido.findViewById(R.id.txtCantidadPedido);
                            Producto p = Util.LISTA_PRODUCTOS_PEDIDO.get(i);
                            p.setCantidad(Integer.parseInt(txtCantidadPedido.getText().toString()));
                            if(Util.USUARIO_SESSION.getIdTipoUsuario() == 2){
                                totalPrecio += (p.getCantidad()*p.getPrecioVendedor().doubleValue());
                            }else{
                                totalPrecio += (p.getCantidad()*p.getPrecioUnitario().doubleValue());
                            }
                        }
                    }
                    TextView vTotalPedido = (TextView) buttonView.getRootView().findViewById(R.id.tvTotalPedido);
                    vTotalPedido.setText("PRECIO TOTAL DE CALZADOS: S/ "+totalPrecio);
                    Util.PRECIO_TOTAL_CALZADOS = totalPrecio;
                }
            });

            EditText txtCantidadPedido = (EditText) convertView.findViewById(R.id.txtCantidadPedido);

            Producto p = lista.get(position);

            Log.i("Producto "+position, p.toString());

            TextView tvNombre = (TextView) convertView.findViewById(R.id.grid_pedido_label_nombre);
            tvNombre.setText("Nombre: "+p.getDescripcion());

            TextView tvCodigo = (TextView) convertView.findViewById(R.id.grid_pedido_label_codigo);
            tvCodigo.setText("Codigo: "+p.getSKU());

            TextView tvColor = (TextView) convertView.findViewById(R.id.grid_pedido_label_color);
            tvColor.setText("Color: "+p.getColor());

            TextView tvTalla = (TextView) convertView.findViewById(R.id.grid_pedido_label_talla);
            tvTalla.setText("Talla: "+p.getNroTalla().toString());

            TextView tvPrecio = (TextView) convertView.findViewById(R.id.grid_pedido_label_precio);
            TextView tvTotalArticulo = (TextView) convertView.findViewById(R.id.tvTotalArticulo);

            if(Util.USUARIO_SESSION.getIdTipoUsuario() == 2){
                tvPrecio.setText("Precio: " + p.getPrecioVendedor() +"(RV)");
                tvTotalArticulo.setText("SubTotal de Calzado: S/ " + p.getPrecioVendedor() +"(RV)");
            }else {
                tvPrecio.setText("Precio: " + p.getPrecioUnitario());
                tvTotalArticulo.setText("SubTotal de Calzado: S/ " + p.getPrecioUnitario());
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.grid_pedido_image);

            Double random = Math.random();
            if (random.intValue() > 9) {
                imageView.setImageResource(R.mipmap.calzado_rojo);
            } else if (random.intValue() > 5) {
                imageView.setImageResource(R.mipmap.calzado_amarillo);
            } else {
                imageView.setImageResource(R.mipmap.calzado_rojo);
            }
//            Picasso picasso = Picasso.with(context);
//            picasso.setIndicatorsEnabled(true);
//            picasso.setLoggingEnabled(true);
            try {
//                Picasso.with(context).load(Util.URL_WEB_SERVICE+"/verImagen?nombre="+p.getNombreImagen()).into(imageView);
            }catch (Exception e){
                e.printStackTrace();
                Log.e("ERROR", e.getMessage());
            }

        return convertView;
    }
}
