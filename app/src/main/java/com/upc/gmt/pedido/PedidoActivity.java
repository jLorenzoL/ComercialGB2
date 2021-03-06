package com.upc.gmt.pedido;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.upc.gmt.comercialgb.R;
import com.upc.gmt.model.Producto;
import com.upc.gmt.util.Util;

import static com.upc.gmt.util.Util.LISTA_PRODUCTOS_PEDIDO;

public class PedidoActivity extends AppCompatActivity {

    ListView lvPedidos;
    TextView tvTotalPedido;

    PedidoArrayAdapter pedidoArrayAdapter;

    Integer posicionItemPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        lvPedidos = (ListView) findViewById(R.id.lvPedidos);
        Log.i("LISTA_PRODUCTOS_PEDIDO", ""+Util.LISTA_PRODUCTOS_PEDIDO.size());
        for(Producto p : Util.LISTA_PRODUCTOS_PEDIDO){
            p.setChecked(false);
        }
        pedidoArrayAdapter = new PedidoArrayAdapter(this, Util.LISTA_PRODUCTOS_PEDIDO);
        lvPedidos.setAdapter(pedidoArrayAdapter);

//        lvPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(view.getId() == R.id.chbArticulo){
//                    Log.d("getId", ""+view.getId());
//                }
//            }
//        });

        tvTotalPedido = (TextView) findViewById(R.id.tvTotalPedido);

        tvTotalPedido.setText("PRECIO TOTAL DE CALZADOS: S/ 0.00");
        Util.PRECIO_TOTAL_CALZADOS = 0.00;

    }

    public void onClickComprar(View v) {
        if(LISTA_PRODUCTOS_PEDIDO.size() == 0){
            Toast.makeText(getApplicationContext(), "NO HAY CALZADOS EN EL PEDIDO", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean validado = true;
//        double totalPrecio = 0.00;

        for (int i = 0; i < Util.LISTA_PRODUCTOS_PEDIDO.size(); i++) {
            Producto p = Util.LISTA_PRODUCTOS_PEDIDO.get(i);
            if (!p.isChecked()) {
                validado = false;
                break;
            }
//            EditText txtCantidadPedido = (EditText) viewPedido.findViewById(R.id.txtCantidadPedido);
//            Producto p = Util.LISTA_PRODUCTOS_PEDIDO.get(i);
//            p.setCantidad(Integer.parseInt(txtCantidadPedido.getText().toString()));
        }

        if (!validado) {
            Toast.makeText(getApplicationContext(), "POR FAVOR, CONFIRMAR TODOS LOS CALZADOS", Toast.LENGTH_SHORT).show();
//            tvTotalPedido.setText("PRECIO TOTAL DE CALZADOS: S/ 0.00");
            return;
        }

//        for (Producto p : Util.LISTA_PRODUCTOS_PEDIDO){
//            if(Util.USUARIO_SESSION.getIdTipoUsuario() == 2){
//                totalPrecio += (p.getCantidad()*p.getPrecioVendedor().doubleValue());
//            }else{
//                totalPrecio += (p.getCantidad()*p.getPrecioUnitario().doubleValue());
//            }
//        }

        //tvTotalPedido.setText("PRECIO TOTAL DE CALZADOS: S/ "+totalPrecio);

        Intent i = new Intent(this, RegistrarPedidoActivity.class);
        startActivity(i);
    }

    public void onQuitarPedido(View v) {
        posicionItemPedido = (Integer) v.getTag();
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setMessage("¿DESEA REMOVER EL CALZADO DE LA LISTA?");
        ad.setTitle("CONFIRMACIÓN");
        ad.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                eliminarPedidoItem();
            }
        });
        ad.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        ad.show();
    }

    private void eliminarPedidoItem() {
       if(posicionItemPedido != null){
           Producto pr = LISTA_PRODUCTOS_PEDIDO.get(posicionItemPedido);
           pedidoArrayAdapter.remove(pr);
           pedidoArrayAdapter.notifyDataSetChanged();
           LISTA_PRODUCTOS_PEDIDO.remove(posicionItemPedido);

           double totalPrecio = 0.00;
           for (Producto p : Util.LISTA_PRODUCTOS_PEDIDO) {
               if (p.isChecked()) {
                   if (Util.USUARIO_SESSION.getIdTipoUsuario() == 2) {
                       totalPrecio += (p.getCantidad() * p.getPrecioVendedor().doubleValue());
                   } else {
                       totalPrecio += (p.getCantidad() * p.getPrecioUnitario().doubleValue());
                   }
               }
           }

           TextView vTotalPedido = (TextView) findViewById(R.id.tvTotalPedido);
           vTotalPedido.setText("PRECIO TOTAL DE CALZADOS: S/ "+String.format("%.2f", totalPrecio));
           Util.PRECIO_TOTAL_CALZADOS = totalPrecio;

       }
    }
}