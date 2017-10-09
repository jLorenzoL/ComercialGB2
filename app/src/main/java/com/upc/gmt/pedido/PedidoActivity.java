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

import java.util.List;

import static com.upc.gmt.util.Util.LISTA_PRODUCTOS_PEDIDO;

public class PedidoActivity extends AppCompatActivity {

    ListView lvPedidos;
    TextView tvTotalPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        lvPedidos = (ListView) findViewById(R.id.lvPedidos);
        List<Producto> lista = LISTA_PRODUCTOS_PEDIDO;
        lvPedidos.setAdapter(new PedidoArrayAdapter(this, lista));

        tvTotalPedido = (TextView) findViewById(R.id.tvTotalPedido);

    }

    public void onClickComprar(View v){
        boolean validado = true;
//        double totalPrecio = 0.00;

        for (int i = 0; i < lvPedidos.getCount(); i++){
            View viewPedido = Util.getViewByPosition(i, lvPedidos);
            CheckBox chbArticulo = (CheckBox) viewPedido.findViewById(R.id.chbArticulo);
            if(!chbArticulo.isChecked()){
                validado = false;
                break;
            }
//            EditText txtCantidadPedido = (EditText) viewPedido.findViewById(R.id.txtCantidadPedido);
//            Producto p = Util.LISTA_PRODUCTOS_PEDIDO.get(i);
//            p.setCantidad(Integer.parseInt(txtCantidadPedido.getText().toString()));
        }

        if(!validado) {
            Toast.makeText(getApplicationContext(), "Por favor, confirmar todos los calzados.", Toast.LENGTH_SHORT).show();
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

        Intent i = new Intent(this,RegistrarPedidoActivity.class);
        startActivity(i);
    }

    public void onQuitarPedido(View v){

        AlertDialog.Builder ad= new AlertDialog.Builder(this);
        ad.setMessage("Desea Quitar pedido de la lista?");
        ad.setTitle("Confirmacion");
        ad.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(PedidoActivity.this, "Pedido Eliminado", Toast.LENGTH_LONG).show();
            }
        });
        ad.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancelar();
            }
        });
        ad.show();
    }
    public void cancelar() {
        finish();
    }
    }
