package com.upc.gmt.pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.upc.gmt.comercialgb.Aumento_credito;
import com.upc.gmt.comercialgb.R;
import com.upc.gmt.util.Util;

import java.util.ArrayList;
import java.util.List;

public class PedidoConsignacionActivity extends AppCompatActivity {

    EditText txtCreditoTotal;
    EditText txtCreditoDisponible;
    EditText txtDeudaPendiente;
    EditText txtCreditoDespues;
    EditText txtTotalConsignacion;
    Spinner spnCuotas;

    List listaCuotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_consignacion);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        txtCreditoTotal = (EditText) findViewById(R.id.txtCreditoTotal);
        txtCreditoDisponible = (EditText) findViewById(R.id.txtCreditoDisponible);
        txtDeudaPendiente = (EditText) findViewById(R.id.txtDeudaPendiente);
        txtCreditoDespues = (EditText) findViewById(R.id.txtCreditoDespues);
        txtTotalConsignacion = (EditText) findViewById(R.id.txtTotalConsignacion);
        spnCuotas = (Spinner) findViewById(R.id.spnCuotas);

        txtCreditoTotal.setText("S/. "+String.format("%.2f", Util.CLIENTE_SESSION.getLineaCreditoActual()));
        txtCreditoDisponible.setText("S/. "+String.format("%.2f", Util.CLIENTE_SESSION.getSaldoLineaCredito()));

        txtCreditoDespues.setText("S/. "+String.format("%.2f", Util.CLIENTE_SESSION.getSaldoLineaCredito()-Util.PRECIO_TOTAL_PAGAR));
        txtTotalConsignacion.setText("S/. "+String.format("%.2f", Util.PRECIO_TOTAL_PAGAR));

        //Lista Precios
        listaCuotas = new ArrayList<>();
        listaCuotas.add("2");
        listaCuotas.add("3");
        listaCuotas.add("4");
        listaCuotas.add("5");
        listaCuotas.add("6");
        listaCuotas.add("7");
        listaCuotas.add("8");

        ArrayAdapter<String> arrayPrecioCalzado = new ArrayAdapter<String>(getApplicationContext(),R.layout.simple_spinner_item,listaCuotas);
        arrayPrecioCalzado.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spnCuotas.setAdapter(arrayPrecioCalzado);

        spnCuotas.setSelection(2);



    }

    public void onSolicitarAumento(View v){
        Intent i = new Intent(this, Aumento_credito.class);
        startActivity(i);
    }
}
