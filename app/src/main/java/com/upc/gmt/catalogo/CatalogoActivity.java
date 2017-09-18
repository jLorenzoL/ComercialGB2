package com.upc.gmt.catalogo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.upc.gmt.bean.ProductoBean;
import com.upc.gmt.comercialgb.R;
import com.upc.gmt.pedido.PedidoActivity;
import com.upc.gmt.util.Util;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public class CatalogoActivity extends AppCompatActivity {

    public String valorPrecio = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /* //CREAMOS LA LISTA DE TIPOS DE CALZADO
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
        */
        //GRILLA DE CALZADOS
        GridView gvCalzados = (GridView) findViewById(R.id.gvCatalogo);
        gvCalzados.setAdapter(new ImagenCalzadoAdapter(this, new ArrayList<ProductoBean>()));
        //EVENTO DE LA GRILLA
        gvCalzados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView)view.findViewById(R.id.grid_item_label_nombre)).getText(),
                        Toast.LENGTH_LONG).show();
                String codigo = ((TextView)view.findViewById(R.id.grid_item_label_codigo)).getText().toString();
                Intent i = new Intent(getApplication(),DetalleCalzadoActivity.class);
                i.putExtra("codigo", codigo);
                startActivity(i);
            }
        });
/*
//        List<String> listaPrecioCalzado = new ArrayList<>();
//        listaPrecioCalzado.add("PRECIOS");
//        listaPrecioCalzado.add("80-99" + "S/.");
//        listaPrecioCalzado.add("100-199 S/.");
//        listaPrecioCalzado.add("200-250 S/.");
//        ArrayAdapter<String> arrayPrecioCalzado = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaPrecioCalzado);
        */
        Spinner spnPrecioCalzado = (Spinner) findViewById(R.id.spnCatalogoPC);
        spnPrecioCalzado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valorPrecio = (String) parent.getItemAtPosition(position);
                Log.i("VALOR", valorPrecio);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*
//        spnPrecioCalzado.setAdapter(arrayPrecioCalzado);

        /*List<String> listaTallaCalzado = new ArrayList<>();
        listaTallaCalzado.add("TALLA");
        listaTallaCalzado.add("36");
        listaTallaCalzado.add("37");
        listaTallaCalzado.add("38");
        listaTallaCalzado.add("39");
        listaTallaCalzado.add("40");
        listaTallaCalzado.add("41");
        ArrayAdapter<String> arrayTallaCalzado = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaTallaCalzado);
        Spinner spnTallaCalzado = (Spinner) findViewById(R.id.spnCatalogoTaC);
        spnTallaCalzado.setAdapter(arrayTallaCalzado);*/

        /*List<String> listaTacoCalzado = new ArrayList<>();
        listaTacoCalzado.add("TACO");
        listaTacoCalzado.add("CHINO");
        listaTacoCalzado.add("PLATAFORMA");
        listaTacoCalzado.add("CUADRADO");
        listaTacoCalzado.add("FINO");
        listaTacoCalzado.add("TAPITA");
        ArrayAdapter<String> arrayTacoCalzado = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listaTacoCalzado);
        Spinner spnTacoCalzado = (Spinner) findViewById(R.id.spnCatalogoTacoC);
        spnTacoCalzado.setAdapter(arrayTacoCalzado);*/

        new HttpRequestTaskPrecios().execute();
        new HttpRequestTaskTallas().execute();
        new HttpRequestTaskTipos().execute();
        new HttpRequestTaskTipoTaco().execute();
        new HttpRequestTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.carrito, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
//                return true;

            case R.id.action_carrito:
                Intent i = new Intent(this, PedidoActivity.class);
                startActivity(i);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, List<ProductoBean>> {
        @Override
        protected List<ProductoBean> doInBackground(Void... params) {
            Log.i("doInBackground", "inicio");
            try {
                String URL = Util.URL_WEB_SERVICE +"/verCatalogo";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//                Object objetos = restTemplate.getForObject(url, Object.class);
//                HttpHeaders requestHeaders = new HttpHeaders();
//                requestHeaders.setContentType(MediaType.APPLICATION_JSON);

//                MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//                body.add("precio", valorPrecio);
//                HttpEntity<?> httpEntity = new HttpEntity<Object>(body, requestHeaders);

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                        .queryParam("precio", valorPrecio);

                ParameterizedTypeReference<List<ProductoBean>> responseType = new ParameterizedTypeReference<List<ProductoBean>>() {};
                ResponseEntity<List<ProductoBean>> respuesta = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null, responseType);
                List<ProductoBean> lista = respuesta.getBody();
                return lista;
            } catch (Exception e) {
                Log.i("Exception", "ERROR");
                Log.e("HttpRequestTask", e.getMessage(), e);
            }
            Log.i("doInBackground", "fin");
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(List<ProductoBean> lista) {
            Log.i("onPostExecute", "inicio");
            Log.i("LISTA", "Tamaño: "+lista.size());
            GridView gvCalzados = (GridView) findViewById(R.id.gvCatalogo);
            gvCalzados.setAdapter(new ImagenCalzadoArrayAdapter(getApplicationContext(), lista));
            Log.i("onPostExecute", "fin");
        }

    }

    /*
    LISTA DE PRECIOS
     */
    private class HttpRequestTaskPrecios extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... params) {
            Log.i("doInBackground", "inicio");
            try {
                String URL = Util.URL_WEB_SERVICE +"/verPrecios";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setContentType(MediaType.APPLICATION_JSON); //copied this from somewhere else, not sure what its for

                MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//                body.add("field", "value");
                HttpEntity<?> httpEntity = new HttpEntity<Object>(body, requestHeaders);

                ParameterizedTypeReference<List<String>> responseType = new ParameterizedTypeReference<List<String>>() {};
                ResponseEntity<List<String>> respuesta = restTemplate.exchange(URL, HttpMethod.GET, null, responseType);
                List<String> lista = respuesta.getBody();
                return lista;
            } catch (Exception e) {
                Log.i("Exception", "ERROR");
                Log.e("HttpRequestTask", e.getMessage(), e);
            }
            Log.i("doInBackground", "fin");
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(List<String> lista) {
            Log.i("onPostExecute", "inicio");
            Log.i("LISTA", "Tamaño: "+lista.size());
            ArrayAdapter<String> arrayPrecioCalzado = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,lista);
            Spinner spnPrecioCalzado = (Spinner) findViewById(R.id.spnCatalogoPC);
            spnPrecioCalzado.setAdapter(arrayPrecioCalzado);
            Log.i("onPostExecute", "fin");
        }

    }

    /*
    Lista de Tallas
     */
    private class HttpRequestTaskTallas extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... params) {
            Log.i("doInBackground", "inicio");
            try {
                String URL = Util.URL_WEB_SERVICE +"/verTallas";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setContentType(MediaType.APPLICATION_JSON); //copied this from somewhere else, not sure what its for

                MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//                body.add("field", "value");
                HttpEntity<?> httpEntity = new HttpEntity<Object>(body, requestHeaders);

                ParameterizedTypeReference<List<String>> responseType = new ParameterizedTypeReference<List<String>>() {};
                ResponseEntity<List<String>> respuesta = restTemplate.exchange(URL, HttpMethod.GET, null, responseType);
                List<String> lista = respuesta.getBody();
                return lista;
            } catch (Exception e) {
                Log.i("Exception", "ERROR");
                Log.e("HttpRequestTask", e.getMessage(), e);
            }
            Log.i("doInBackground", "fin");
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(List<String> lista) {
            Log.i("onPostExecute", "inicio");
            Log.i("LISTA", "Tamaño: "+lista.size());
            ArrayAdapter<String> arrayTallaCalzado = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,lista);
            Spinner spnTallaCalzado = (Spinner) findViewById(R.id.spnCatalogoTaC);
            spnTallaCalzado.setAdapter(arrayTallaCalzado);
            Log.i("onPostExecute", "fin");
        }

    }

    //Lista de Tipos de Calzado
    private class HttpRequestTaskTipos extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... params) {
            Log.i("doInBackground", "inicio");
            try {
                String URL = Util.URL_WEB_SERVICE +"/verTipoCalzado";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setContentType(MediaType.APPLICATION_JSON); //copied this from somewhere else, not sure what its for

                MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//                body.add("field", "value");
                HttpEntity<?> httpEntity = new HttpEntity<Object>(body, requestHeaders);

                ParameterizedTypeReference<List<String>> responseType = new ParameterizedTypeReference<List<String>>() {};
                ResponseEntity<List<String>> respuesta = restTemplate.exchange(URL, HttpMethod.GET, null, responseType);
                List<String> lista = respuesta.getBody();
                return lista;
            } catch (Exception e) {
                Log.i("Exception", "ERROR");
                Log.e("HttpRequestTask", e.getMessage(), e);
            }
            Log.i("doInBackground", "fin");
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(List<String> lista) {
            Log.i("onPostExecute", "inicio");
            Log.i("LISTA", "Tipo de Calzado: "+lista.size());
            ArrayAdapter<String> arrayTipoCalzado = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,lista);
            Spinner spnTipoCalzado = (Spinner) findViewById(R.id.spnCatalogoTC);
            spnTipoCalzado.setAdapter(arrayTipoCalzado);
            Log.i("onPostExecute", "fin");
        }

    }

    //Lista de Tipos de Taco
    private class HttpRequestTaskTipoTaco extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... params) {
            Log.i("doInBackground", "inicio");
            try {
                String URL = Util.URL_WEB_SERVICE +"/verTipoTacoCalzado";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setContentType(MediaType.APPLICATION_JSON); //copied this from somewhere else, not sure what its for

                MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//                body.add("field", "value");
                HttpEntity<?> httpEntity = new HttpEntity<Object>(body, requestHeaders);

                ParameterizedTypeReference<List<String>> responseType = new ParameterizedTypeReference<List<String>>() {};
                ResponseEntity<List<String>> respuesta = restTemplate.exchange(URL, HttpMethod.GET, null, responseType);
                List<String> lista = respuesta.getBody();
                return lista;
            } catch (Exception e) {
                Log.i("Exception", "ERROR");
                Log.e("HttpRequestTask", e.getMessage(), e);
            }
            Log.i("doInBackground", "fin");
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(List<String> lista) {
            Log.i("onPostExecute", "inicio");
            Log.i("LISTA", "Tipo de Taco: "+lista.size());
            ArrayAdapter<String> arrayTipoTacoCalzado = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,lista);
            Spinner spnTipoTacoCalzado = (Spinner) findViewById(R.id.spnCatalogoTacoC);
            spnTipoTacoCalzado.setAdapter(arrayTipoTacoCalzado);
            Log.i("onPostExecute", "fin");
        }

    }

}
