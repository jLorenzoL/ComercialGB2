package com.upc.gmt.comercialgb;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.upc.gmt.model.Usuario;
import com.upc.gmt.util.Util;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtPassword;

    String usuario;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

    }
    public void onValidarIngreso(View v){
        usuario = txtUsuario.getText().toString();
        password = txtPassword.getText().toString();
        new HttpRequestTaskLogin().execute();
        //Intent i = new Intent(getApplicationContext(),com.upc.gmt.comercialgb.MenuPrincipalActivity.class);
        //startActivity(i);
    }

    public void onRecuperClave(View v) {
        Intent i = new Intent(getApplicationContext(),com.upc.gmt.comercialgb.RecuperarClaveActivity.class);
        startActivity(i);
    }

    public void onRegistrar(View v) {
        Intent i = new Intent(getApplicationContext(),com.upc.gmt.comercialgb.AgregarUsuarioActivity.class);
        startActivity(i);
    }


    private class HttpRequestTaskLogin extends AsyncTask<Void, Void, Usuario> {
        @Override
        protected Usuario doInBackground(Void... params) {
            try {
                String URL = Util.URL_WEB_SERVICE + "/login";
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                        .queryParam("codUsuario", usuario)
                        .queryParam("password", password);

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Usuario Usuario = restTemplate.getForObject(builder.build().encode().toUri(), Usuario.class);
                Log.i("Usuario", usuario);

                return Usuario;
            } catch (Exception e) {
                Log.e(this.getClass().getName(), e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            if(null != usuario) {
                Util.USUARIO_SESSION = usuario;
                Toast.makeText(LoginActivity.this,
                        "BIENVENIDO " + usuario.getCodUsuario()
                        , Toast.LENGTH_SHORT).show();
                if(Util.REGRESAR_A_CATALOGO){
                    finish();
                }else{
                    Intent i = new Intent(getApplicationContext(),com.upc.gmt.comercialgb.MenuPrincipalActivity.class);
                    startActivity(i);
                }
            }else {
                Util.USUARIO_SESSION = new Usuario();
                Util.USUARIO_SESSION.setIdTipoUsuario(1);
            }

        }

    }
}


