package com.upc.gmt.pedido;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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
        lvPedidos.setAdapter(new PedidoArrayAdapter(this, lista));

    }
}
