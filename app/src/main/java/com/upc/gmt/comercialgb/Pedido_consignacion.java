package com.upc.gmt.comercialgb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Pedido_consignacion extends AppCompatActivity {

    Spinner spnCuota;
    List<String> listaCuotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_consignacion);

        spnCuota = (Spinner) findViewById(R.id.spnCuotas);

        listaCuotas = new ArrayList<>();
        listaCuotas.add("Cuotas");
        listaCuotas.add("1 Cuota");
        listaCuotas.add("2 Cuotas");
        listaCuotas.add("3 Cuotas");
        listaCuotas.add("4 Cuotas");
        listaCuotas.add("5 Cuotas");

        ArrayAdapter<String> arrayCuota = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, listaCuotas);
        arrayCuota.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spnCuota.setAdapter(arrayCuota);
    }
}
