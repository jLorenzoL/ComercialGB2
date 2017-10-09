package com.upc.gmt.catalogo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.gmt.comercialgb.R;
import com.upc.gmt.model.Producto;
import com.upc.gmt.pedido.PedidoActivity;
import com.upc.gmt.util.Util;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetalleCalzadoActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    LinearLayout lyDetalleCalzado;

    TextView tvDetalleNombre;
    TextView tvDetalleCodigo;
    TextView tvDetallePrecio;
    Spinner spnColores;
    Spinner spnTallas;
    TextView tvDetalleMaterial;

    ImageView imageView;
    String idProducto;
    String idColor;
    String nroTalla;

    List<String> listaMateriales;
    Map<String, String> mapaColores;
    List<String> listaTallas;

    List<Producto> listaColorTalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_calzado);

        Bundle extras = getIntent().getExtras();

        idProducto = extras.getString("idProducto");
        Log.i("onCreate-idProducto", idProducto);
        tvDetalleNombre = (TextView) findViewById(R.id.tvDetalleNombre);
        tvDetalleCodigo = (TextView) findViewById(R.id.tvDetalleCodigo);
        tvDetallePrecio = (TextView) findViewById(R.id.tvDetallePrecio);
        spnColores = (Spinner)findViewById(R.id.spnDetalleColores);
        spnTallas = (Spinner)findViewById(R.id.spnDetalleTallas);
        tvDetalleMaterial = (TextView) findViewById(R.id.tvDetalleMaterial);

        spnColores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String desColor = (String) parent.getItemAtPosition(position);
                String idColorNuevo = "";
                for(Map.Entry<String, String> entry : mapaColores.entrySet()) {
                    if(desColor.equals(entry.getValue())){
                        idColorNuevo = entry.getKey();
                        break;
                    }
                }
                Log.i("idColor", idColorNuevo);
                if(!idColorNuevo.equals("") && !idColor.equals(idColorNuevo)){
                    idColor = idColorNuevo;
                    progressDialog.show();
                    new HttpRequestTaskTallas().execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spnTallas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nroTalla = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        lyDetalleCalzado = (LinearLayout) findViewById(R.id.lyDetalleCalzado);
        for (int i = 0; i <= 4; i++) {
            imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setClickable(true);
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(2, 2, 2, 2);
            Double random = Math.random()*10;
            if(random.intValue() > 6){
                imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.calzado_verde));
            }else if(random.intValue() > 3){
                imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.calzado_amarillo));
            }else{
                imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.calzado_rojo));
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(imageView.getDrawable());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int id = v.getId();
//                    ImageView iv = (ImageView) v.findViewById(id);
//                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Intent i = new Intent(getApplicationContext(), FullScreenImageActivity.class);
                    BitmapDrawable d = (BitmapDrawable) v.getTag();
                    Bitmap bmp = d.getBitmap();
//                    Bitmap bmp = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    i.putExtra("nombre", "Calzado Demo "+byteArray.length);
                    i.putExtra("imagen", byteArray);
                    startActivity(i);
                }
            });

            lyDetalleCalzado.addView(imageView);
        }

//        lyDetalleCalzado.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int id = v.getId();
//                ImageView iv = (ImageView) v.findViewById(R.id);
//                iv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                iv.setScaleType(ImageView.ScaleType.FIT_XY);
//            }
//        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando Calzado...");
        progressDialog.show();
        new HttpRequestTaskDetalleCalzado().execute();
    }

    @Override
    protected void onStart() {
//        progressDialog.show();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.carrito, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_carrito:
                Intent i = new Intent(this, PedidoActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickAgregarPedido(View v){
        Log.i("DATOS PEDIDO", idProducto+"-"+idColor+"-"+nroTalla.substring(0,2));
        Producto p = CatalogoActivity.productoSeleccionado;
        p.setIdProducto(Integer.parseInt(idProducto));
        p.setIdColor(idColor);
        p.setNroTalla(Integer.parseInt(nroTalla.substring(0,2)));
        p.setColor(spnColores.getSelectedItem().toString());
        Util.LISTA_PRODUCTOS_PEDIDO.add(p);
        Toast.makeText(DetalleCalzadoActivity.this, "EL CALZADO "+p.getDescripcion()+" FUE AGREGADO AL PEDIDO", Toast.LENGTH_SHORT).show();
        finish();
    }

    private class HttpRequestTaskDetalleCalzado extends AsyncTask<Void, Void, Producto> {
        @Override
        protected Producto doInBackground(Void... params) {
            Log.i("doInBackground", "HttpRequestTaskDetalleCalzado");
            try {
                String URL = Util.URL_WEB_SERVICE +"/verDetalleCalzado";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                        .queryParam("idProducto", idProducto);
                Log.i("URL", builder.toUriString());
                ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {};
                ResponseEntity<Map<String, Object>> respuesta = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null, responseType);
                Map<String, Object> mapa = respuesta.getBody();
                Log.i("respuesta", mapa.toString());
                ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
                List<Producto> lista = mapper.convertValue(mapa.get("detalle"), new TypeReference<List<Producto>>(){});
                listaMateriales = new ArrayList<>();
                for (Producto p:lista) {
                    listaMateriales.add(p.getComponente()+": "+p.getMaterial());
                }
                List<Producto> colores = mapper.convertValue(mapa.get("colores"), new TypeReference<List<Producto>>(){});
                mapaColores = new HashMap<>();
                for (Producto p:colores) {
                    mapaColores.put(p.getIdColor(),p.getColor());
                }
                List<Producto> tallas = mapper.convertValue(mapa.get("tallas"), new TypeReference<List<Producto>>(){});
                if(tallas.size() > 0){
                    nroTalla = String.valueOf(tallas.get(0).getNroTalla());
                }
                listaTallas = new ArrayList<>();
                for (Producto p:tallas) {
                    listaTallas.add(p.getNroTalla() + " (Stock: "+ p.getStockVenta() + ")");
                }
                return lista.get(0);
            } catch (Exception e) {
                Log.i("Exception", "ERROR");
                Log.e("HttpRequestTask", e.getMessage(), e);
            }
            Log.i("doInBackground", "fin");
            return new Producto();
        }

        @Override
        protected void onPostExecute(Producto p) {
            Log.i("onPostExecute", "HttpRequestTaskDetalleCalzado");
            Log.i("Producto", p.toString());
            if(p.getIdProducto() != null) {
                tvDetalleNombre.setText("Nombre  :" + p.getDescripcion());
                tvDetalleCodigo.setText("Codigo  :" + p.getSKU());
                tvDetallePrecio.setText("Precio  :" + p.getPrecioUnitario());
                List<String> colores = new ArrayList<>();
                int contador = 1;
                for (Map.Entry<String, String> entry : mapaColores.entrySet()) {
                    if (contador == 1) {
                        idColor = entry.getKey();
                    }
                    colores.add(entry.getValue());
                    contador++;
                }
                ArrayAdapter spnColores = new ArrayAdapter(getApplicationContext(), R.layout.simple_spinner_item, colores);
                spnColores.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                Spinner spn = (Spinner) findViewById(R.id.spnDetalleColores);
                spn.setAdapter(spnColores);
                ArrayAdapter spnTallas = new ArrayAdapter(getApplicationContext(), R.layout.simple_spinner_item, listaTallas);
                spnTallas.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                Spinner spn1 = (Spinner) findViewById(R.id.spnDetalleTallas);
                spn1.setAdapter(spnTallas);
                String material = "";
                for (String m : listaMateriales) {
                    material += m + System.getProperty("line.separator");
                }
                tvDetalleMaterial.setText(material);
                progressDialog.dismiss();
            }
            Log.i("onPostExecute", "fin");
        }

    }

    private class HttpRequestTaskTallas extends AsyncTask<Void, Void, List<Producto>> {
        @Override
        protected List<Producto> doInBackground(Void... params) {
            Log.i("doInBackground", "HttpRequestTaskTallas");
            try {
                String URL = Util.URL_WEB_SERVICE +"/verDetalleCalzadoTallas";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                        .queryParam("idProducto", idProducto)
                        .queryParam("idColor", idColor);
                Log.i("URL", builder.toUriString());
                ParameterizedTypeReference<List<Producto>> responseType = new ParameterizedTypeReference<List<Producto>>() {};
                ResponseEntity<List<Producto>> respuesta = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null, responseType);
                List<Producto> lista = respuesta.getBody();
                Log.i("respuesta", lista.toString());
                return lista;
            } catch (Exception e) {
                Log.i("Exception", "ERROR");
                Log.e("HttpRequestTask", e.getMessage(), e);
            }
            Log.i("doInBackground", "fin");
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(List<Producto> lista) {
            Log.i("onPostExecute", "HttpRequestTaskTallas");
            Log.i("LISTA", "Tamaño: "+lista.size());
            listaColorTalla = lista;
            List<String> items = new ArrayList<>();
            for (Producto p:lista) {
                items.add(p.getNroTalla().toString() + " (Stock: "+ p.getStockVenta() + ")");
            }
            ArrayAdapter<String> arrayTallaCalzado = new ArrayAdapter<String>(getApplicationContext(),R.layout.simple_spinner_item,items);
            arrayTallaCalzado.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            spnTallas.setAdapter(arrayTallaCalzado);
            progressDialog.dismiss();
            Log.i("onPostExecute", "fin");
        }

    }

}
