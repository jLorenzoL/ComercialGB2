package com.upc.gmt.pedido;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.upc.gmt.bean.ProductoBean;
import com.upc.gmt.comercialgb.R;

import java.util.ArrayList;
import java.util.List;

public class PedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        ListView lvPedidos = (ListView) findViewById(R.id.lvPedidos);
        List<ProductoBean> lista = new ArrayList<>();
        ProductoBean p = new ProductoBean();
        p.setNombre("Calzado 1");
        p.setCodigo("COD 1");
        p.setPrecio("129.99");
        lista.add(p);
        ProductoBean p2 = new ProductoBean();
        p2.setNombre("Calzado 2");
        p2.setCodigo("COD 2");
        p2.setPrecio("99.99");
        lista.add(p2);
        lvPedidos.setAdapter(new PedidoArrayAdapter(this, lista));

    }

    public void onClickComprar(View v){
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
