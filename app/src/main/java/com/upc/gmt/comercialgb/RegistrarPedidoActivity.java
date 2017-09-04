package com.upc.gmt.comercialgb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.upc.gmt.comercialgb.R.id.btnRegistrar;

public class RegistrarPedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pedido);
    }

    public void onRegistrarPedidoAlert(View v){
        AlertDialog.Builder ad= new AlertDialog.Builder(this);
        ad.setMessage("Desea Registrar?");
        ad.setTitle("Confirmacion");
        ad.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(RegistrarPedidoActivity.this, "Registro Exitoso", Toast.LENGTH_LONG).show();
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
