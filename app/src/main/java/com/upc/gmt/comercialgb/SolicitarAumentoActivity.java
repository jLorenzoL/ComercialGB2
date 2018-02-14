package com.upc.gmt.comercialgb;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.upc.gmt.model.Cliente;
import com.upc.gmt.util.Util;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class SolicitarAumentoActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    EditText txtCreditoTotalAumento;
    EditText txtCreditoDisponibleAumento;
    EditText txtDeudaPendienteAumento;
    EditText txtCantidadAumento;

    int cantidadINT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aumento_credito);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        progressDialog = new ProgressDialog(this);

        txtCreditoTotalAumento = (EditText) findViewById(R.id.txtCreditoTotalAumento);
        txtCreditoDisponibleAumento = (EditText) findViewById(R.id.txtCreditoDisponibleAumento);
        txtDeudaPendienteAumento = (EditText) findViewById(R.id.txtDeudaPendienteAumento);

        txtCantidadAumento = (EditText) findViewById(R.id.txtCantidadAumento);

        new HttpRequestTaskClienteAumento().execute();
    }

    public void onRegresarMenu(View v){
        finish();
    }

    public void onSolicitarAumento(View v){
        String cantidad = txtCantidadAumento.getText().toString();
        if("".equals(cantidad)){
            Toast.makeText(getApplicationContext(),"INGRESAR UNA CANTIDAD MAYOR A 0",Toast.LENGTH_LONG).show();return;
        }
        cantidadINT = Integer.parseInt(cantidad);
        if(cantidadINT < 1){
            Toast.makeText(getApplicationContext(),"INGRESAR UNA CANTIDAD MAYOR A 0",Toast.LENGTH_LONG).show();return;
        }

        progressDialog.setTitle("Procesando...");
        progressDialog.show();
        new HttpRequestTaskRegistrarAumento().execute();

    }

    private class HttpRequestTaskClienteAumento extends AsyncTask<Void, Void, Cliente> {
        @Override
        protected Cliente doInBackground(Void... params) {
            try {
                String URL = Util.URL_WEB_SERVICE + "/cliente";
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                        .queryParam("idCliente", Util.USUARIO_SESSION.getIdCliente());

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Cliente cliente = restTemplate.getForObject(builder.build().encode().toUri(), Cliente.class);
                Log.i("Cliente", cliente.toString());

                return cliente;
            } catch (Exception e) {
                Log.e(this.getClass().getName(), e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Cliente cliente) {
            if(null != cliente) {
                Util.CLIENTE_SESSION = cliente;
                txtCreditoTotalAumento.setText("S/. "+String.format("%.2f", cliente.getLineaCreditoActual()));
                txtCreditoDisponibleAumento.setText("S/. "+String.format("%.2f", cliente.getSaldoLineaCredito()));
                txtDeudaPendienteAumento.setText("S/. "+String.format("%.2f", cliente.getLineaCreditoActual()-cliente.getSaldoLineaCredito()));
            }else {
                Util.CLIENTE_SESSION = new Cliente();
            }
        }

    }

    private class HttpRequestTaskRegistrarAumento extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... params) {
            try {
                String URL = Util.URL_WEB_SERVICE + "/registrarSolicitud";
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                        .queryParam("idCliente", Util.USUARIO_SESSION.getIdCliente())
                        .queryParam("codUsuario", Util.USUARIO_SESSION.getCodUsuario())
                        .queryParam("montoIncrementoCredito", cantidadINT);

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Log.i("URL", builder.toUriString());
                Integer i = restTemplate.getForObject(builder.build().encode().toUri(), Integer.class);
                Log.i("Resultado", i.toString());
                return i;
            } catch (Exception e) {
                Log.e(this.getClass().getName(), e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer i) {
            progressDialog.dismiss();
            if(i == null || i == 0) {
                Toast.makeText(getApplicationContext(), "NO SE PUDO REGISTRAR LA SOLICITUD", Toast.LENGTH_LONG).show();
            }
            txtCantidadAumento.setText("");
        }

    }

}
