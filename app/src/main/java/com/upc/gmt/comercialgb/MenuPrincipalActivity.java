package com.upc.gmt.comercialgb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.net.Inet4Address;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void onIngresarMantenimientoCalzado(View v){
        Intent i = new Intent(getApplicationContext(),com.upc.gmt.comercialgb.MantenimientoCalzadoActivity.class);
        startActivity(i);
    }

    public void onIngresarRegistroPedido(View v) {
        Intent i = new Intent(getApplicationContext(),com.upc.gmt.comercialgb.RegistrarPedidoActivity.class);
        startActivity(i);
    }

}
