package com.upc.gmt.catalogo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.upc.gmt.bean.ProductoBean;
import com.upc.gmt.comercialgb.R;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DetalleCalzadoActivity extends AppCompatActivity {

    ImageView imageView;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_calzado);

        Bundle extras = getIntent().getExtras();

        codigo = extras.getString("codigo");


        LinearLayout lyDetalleCalzado = (LinearLayout) findViewById(R.id.lyDetalleCalzado);
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
    private class HttpRequestTaskDetalleCalzado extends AsyncTask<Void, Void, ProductoBean> {
        @Override
        protected ProductoBean doInBackground(Void... params) {
            Log.i("doInBackground", "inicio");
            try {
                String URL = Util.URL_WEB_SERVICE +"/verDetalleCalzado";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//                HttpHeaders requestHeaders = new HttpHeaders();
//                requestHeaders.setContentType(MediaType.APPLICATION_JSON);

//                MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//                HttpEntity<?> httpEntity = new HttpEntity<Object>(body, requestHeaders);

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                        .queryParam("codigo", codigo);

                ParameterizedTypeReference<ProductoBean> responseType = new ParameterizedTypeReference<ProductoBean>() {};
                ResponseEntity<ProductoBean> respuesta = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null, responseType);
                ProductoBean p = respuesta.getBody();
                return p;
            } catch (Exception e) {
                Log.i("Exception", "ERROR");
                Log.e("HttpRequestTask", e.getMessage(), e);
            }
            Log.i("doInBackground", "fin");
            return new ProductoBean();
        }

        @Override
        protected void onPostExecute(ProductoBean p) {
            Log.i("onPostExecute", "inicio");
            Log.i("ProductoBean", p.toString());
            TextView tvDetalleNombre = (TextView) findViewById(R.id.tvDetalleNombre); tvDetalleNombre.setText("Nombre  :" + p.getNombre());
            TextView tvDetalleCodigo = (TextView) findViewById(R.id.tvDetalleCodigo); tvDetalleCodigo.setText("Codigo  :" + p.getCodigo());
            TextView tvDetallePrecio = (TextView) findViewById(R.id.tvDetallePrecio); tvDetallePrecio.setText("Precio  :" + p.getPrecio());
            Spinner spn = (Spinner)findViewById(R.id.spnDetalleTalla);
  //          spn.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, p.getListaTallas().t oString());
            TextView tvDetalleMaterial = (TextView) findViewById(R.id.tvDetalleMaterial); tvDetalleMaterial.setText("Material Calzado  :" + p.getMaterialCalzado());
            TextView tvDetalleTaco = (TextView) findViewById(R.id.tvDetalleTaco); tvDetalleTaco.setText("Material Taco  :" + p.getMaterialTaco());
            TextView tvDetalleForro = (TextView) findViewById(R.id.tvDetalleForro); tvDetalleForro.setText("Material de Forro  :" + p.getMaterialForro());
            TextView tvDetallePlanta = (TextView) findViewById(R.id.tvDetallePlanta); tvDetallePlanta.setText("Material de Planta  :" + p.getMaterialPlanta());
            TextView tvDetallePlantilla = (TextView) findViewById(R.id.tvDetallePlantilla); tvDetallePlantilla.setText("Material de Plantilla  :" + p.getMaterialPlantilla());
 //           Spinner spnDetalleTalla = (Spinner) findViewById(R.id.spnDetalleTalla); spnDetalleTalla(p.getListaTallas().toString()):



            Log.i("onPostExecute", "fin");
        }

    }

}
