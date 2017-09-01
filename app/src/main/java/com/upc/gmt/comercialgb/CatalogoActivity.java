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

        List<String> listaPrecioCalzado = new ArrayList<>();
        listaPrecioCalzado.add("PRECIOS");
        listaPrecioCalzado.add("80-99" + "S/.");
        listaPrecioCalzado.add("100-199 S/.");
        listaPrecioCalzado.add("200-250 S/.");
        ArrayAdapter<String> arrayPrecioCalzado = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaPrecioCalzado);
        Spinner spnPrecioCalzado = (Spinner) findViewById(R.id.spnCatalogoPC);
        spnPrecioCalzado.setAdapter(arrayPrecioCalzado);

        List<String> listaTallaCalzado = new ArrayList<>();
        listaTallaCalzado.add("TALLA");
        listaTallaCalzado.add("36");
        listaTallaCalzado.add("37");
        listaTallaCalzado.add("38");
        listaTallaCalzado.add("39");
        listaTallaCalzado.add("40");
        listaTallaCalzado.add("41");
        ArrayAdapter<String> arrayTallaCalzado = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaTallaCalzado);
        Spinner spnTallaCalzado = (Spinner) findViewById(R.id.spnCatalogoTaC);
        spnTallaCalzado.setAdapter(arrayTallaCalzado);

        List<String> listaTacoCalzado = new ArrayList<>();
        listaTacoCalzado.add("TACO");
        listaTacoCalzado.add("CHINO");
        listaTacoCalzado.add("PLATAFORMA");
        listaTacoCalzado.add("CUADRADO");
        listaTacoCalzado.add("FINO");
        listaTacoCalzado.add("TAPITA");
        ArrayAdapter<String> arrayTacoCalzado = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaTacoCalzado);
        Spinner spnTacoCalzado = (Spinner) findViewById(R.id.spnCatalogoTacoC);
        spnTacoCalzado.setAdapter(arrayTacoCalzado);
    }


}
