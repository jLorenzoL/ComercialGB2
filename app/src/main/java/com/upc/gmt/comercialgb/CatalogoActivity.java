package com.upc.gmt.comercialgb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CatalogoActivity extends AppCompatActivity {

    static final String[] listaCalzados = new String[]{"rojo","amarillo","verde"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        //CREAMOS LA LISTA DE TIPOS DE CALZADO
        List<String> listaTipoCalzado = new ArrayList<>();
        listaTipoCalzado.add("TODOS LOS TIPOS");
        listaTipoCalzado.add("BOTAS");
        listaTipoCalzado.add("BOTINES");
        listaTipoCalzado.add("SANDALIAS");
        //DECLARAMOS EL ARRAY ADAPTER PARA EL TIPO DE LISTA SPINNER
        ArrayAdapter<String> arrayTipoCalzado = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaTipoCalzado);
        //DECLARAMOS EL SPINNER
        Spinner spnTipoCalzado = (Spinner) findViewById(R.id.spnCatalogoTC);
        //ASIGNAMOS EL ADAPTER AL SPINNER
        spnTipoCalzado.setAdapter(arrayTipoCalzado);

        //GRILLA DE CALZADOS
        GridView gvCalzados = (GridView) findViewById(R.id.gvCatalogo);
        gvCalzados.setAdapter(new ImagenCalzadoAdapter(this, listaCalzados));
        //EVENTO DE LA GRILLA
        gvCalzados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView)view.findViewById(R.id.grid_item_label)).getText(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
