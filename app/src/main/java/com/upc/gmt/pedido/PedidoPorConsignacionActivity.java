package com.upc.gmt.pedido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.upc.gmt.comercialgb.R;

import java.util.ArrayList;
import java.util.List;

public class PedidoPorConsignacionActivity extends AppCompatActivity {

    Spinner spnCuota;
    List<String> numCuota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_por_consignacion);

        numCuota = new ArrayList<>();
        numCuota.add("Seleccione Cuota");
        numCuota.add("1 Cuota");
        numCuota.add("2 Cuotas");
        numCuota.add("3 Cuotas");
        numCuota.add("4 Cuotas");
        numCuota.add("5 Cuotas");

        ArrayAdapter<String> arrayCuota = new ArrayAdapter<>(getApplicationContext(),R.layout.simple_spinner_item,numCuota);
        arrayCuota.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spnCuota = (Spinner) findViewById(R.id.spnCuota);
        spnCuota.setAdapter(arrayCuota);
    }
}
