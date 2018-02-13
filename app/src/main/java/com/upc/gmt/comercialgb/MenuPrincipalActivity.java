package com.upc.gmt.comercialgb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.upc.gmt.catalogo.CatalogoActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);



    }

//    public void onIngresarMantenimientoCalzado(View v){
//        Intent i = new Intent(getApplicationContext(),com.upc.gmt.comercialgb.MantenimientoCalzadoActivity.class);
//        startActivity(i);
//    }

//    public void onIngresarRegistroPedido(View v) {
//        Intent i = new Intent(getApplicationContext(),RegistrarPedidoActivity.class);
//        startActivity(i);
//    }

    public void onAbrirCatalogo(View v) {
        Intent i = new Intent(getApplicationContext(),CatalogoActivity.class);
        startActivity(i);
    }

//    public void onMantenimientoUsuario(View v) {
//        Intent i = new Intent(getApplicationContext(),com.upc.gmt.comercialgb.MantenimientoUsuarioActivity.class);
//        startActivity(i);
//    }

    public void onAumentoLinea(View v){
        Intent i = new Intent(getApplicationContext(),SolicitarAumentoActivity.class);
        startActivity(i);
    }
}
