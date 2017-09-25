package com.upc.gmt.catalogo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.gmt.comercialgb.R;
import com.upc.gmt.model.Producto;
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

    LinearLayout lyDetalleCalzado;

    TextView tvDetalleNombre;
    TextView tvDetalleCodigo;
    TextView tvDetallePrecio;
    Spinner spnColores;
    Spinner spnTallas;
    TextView tvDetalleMaterial;

    ImageView imageView;
    String codigo;

    List<String> listaMateriales;
    Map<String, String> mapaColores;
    List<String> listaTallas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_calzado);

        Bundle extras = getIntent().getExtras();

        codigo = extras.getString("codigo");

        tvDetalleNombre = (TextView) findViewById(R.id.tvDetalleNombre);
        tvDetalleCodigo = (TextView) findViewById(R.id.tvDetalleCodigo);
        tvDetallePrecio = (TextView) findViewById(R.id.tvDetallePrecio);
        spnColores = (Spinner)findViewById(R.id.spnDetalleColores);
        spnTallas = (Spinner)findViewById(R.id.spnDetalleTallas);
        tvDetalleMaterial = (TextView) findViewById(R.id.tvDetalleMaterial);

        lyDetalleCalzado = (LinearLayout) findViewById(R.id.lyDetalleCalzado);
        for (int i = 0; i <= 4; i++) {
            imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setClickable(true);
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(2, 2, 2, 2);
            if(i>3){
                imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.calzado_verde));
            }else if(i>1){
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

        new HttpRequestTaskDetalleCalzado().execute();
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
                        .queryParam("idProducto", codigo);
                Log.i("URL", builder.toUriString());
//                ParameterizedTypeReference<Producto> responseType = new ParameterizedTypeReference<Producto>() {};
//                ResponseEntity<Producto> respuesta = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null, responseType);
//                Producto p = respuesta.getBody();

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
            tvDetalleNombre.setText("Nombre  :" + p.getDescripcion());
            tvDetalleCodigo.setText("Codigo  :" + p.getSKU());
            tvDetallePrecio.setText("Precio  :" + p.getPrecioUnitario());
            List<String> colores = new ArrayList<>();
            for(Map.Entry<String, String> entry : mapaColores.entrySet()) {
                colores.add(entry.getValue());
            }
            spnColores.setAdapter(
                    new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, colores)
            );
            spnTallas.setAdapter(
                    new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaTallas)
            );
            String material = "";
            for(String m:listaMateriales){
                material += m + System.getProperty("line.separator");
            }
            tvDetalleMaterial.setText(material);
            Log.i("onPostExecute", "fin");
        }

    }

}
