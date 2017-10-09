package com.upc.gmt.pedido;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
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
        pedidoArrayAdapter = new PedidoArrayAdapter(this, LISTA_PRODUCTOS_PEDIDO);
        lvPedidos.setAdapter(pedidoArrayAdapter);

        tvTotalPedido = (TextView) findViewById(R.id.tvTotalPedido);

    }

    public void onClickComprar(View v) {
        if(Util.LISTA_PRODUCTOS_PEDIDO.size() == 0){
            Toast.makeText(getApplicationContext(), "NO HAY CALZADOS EN EL PEDIDO", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean validado = true;
//        double totalPrecio = 0.00;

        for (int i = 0; i < lvPedidos.getCount(); i++) {
            View viewPedido = Util.getViewByPosition(i, lvPedidos);
            CheckBox chbArticulo = (CheckBox) viewPedido.findViewById(R.id.chbArticulo);
            if (!chbArticulo.isChecked()) {
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
        ad.setMessage("¿Desea remover el calzado de la lista?");
        ad.setTitle("CONFIRMACIÓN");
        ad.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                eliminarPedidoItem();
            }
        });
        ad.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        ad.show();
    }

    private void eliminarPedidoItem() {
       if(posicionItemPedido != null){
           Producto p = Util.LISTA_PRODUCTOS_PEDIDO.get(posicionItemPedido);
           pedidoArrayAdapter.remove(p);
           pedidoArrayAdapter.notifyDataSetChanged();
           Util.LISTA_PRODUCTOS_PEDIDO.remove(posicionItemPedido);
       }
    }
}