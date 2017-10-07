package com.upc.gmt.comercialgb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.upc.gmt.catalogo.CatalogoActivity;

import java.util.ArrayList;
import java.util.List;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        List<String> lista = new ArrayList<>();
        lista.add("A");
        lista.add("B");
        lista.add("C");
        ArrayAdapter adapter2 = new ArrayAdapter<>(getApplicationContext(),R.layout.simple_spinner_item,lista);
        adapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        Spinner spn = (Spinner) findViewById(R.id.spnPrueba2);
        spn.setAdapter(adapter2);
    }

    public void onIngresarMantenimientoCalzado(View v){
        Intent i = new Intent(getApplicationContext(),com.upc.gmt.comercialgb.MantenimientoCalzadoActivity.class);
        startActivity(i);
    }

    public void onIngresarRegistroPedido(View v) {
        Intent i = new Intent(getApplicationContext(),com.upc.gmt.comercialgb.RegistrarPedidoActivity.class);
        startActivity(i);
    }

    public void onAbrirCatalogo(View v) {
        Intent i = new Intent(getApplicationContext(),CatalogoActivity.class);
        startActivity(i);
    }

    public void onMantenimientoUsuario(View v) {
        Intent i = new Intent(getApplicationContext(),com.upc.gmt.comercialgb.MantenimientoUsuarioActivity.class);
        startActivity(i);
    }
}
