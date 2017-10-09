package com.upc.gmt.pedido;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.upc.gmt.comercialgb.R;

public class RegistrarPedidoActivity extends AppCompatActivity
        implements TipoEntregaFragment.OnFragmentInteractionListener,
        TipoPagoFragment.OnFragmentInteractionListener,
        ComprobantePagoFragment.OnFragmentInteractionListener {

    static int tipoEntrega = 0;
    static int tipoPago = 0;
    static int tipoComprobante = 0;

    ListView lvRegistrarPedido;
    FrameLayout fragPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pedido);

        String[] items = {"TIPO DE ENTREGA","TIPO DE PAGO","COMPROBANTE DE PAGO"};
        lvRegistrarPedido = (ListView) findViewById(R.id.lvRegistrarPedido);
        lvRegistrarPedido.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_listview_item,items));

        lvRegistrarPedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Log.i("ITEM", item);
                if(item.equals("TIPO DE ENTREGA")){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragPedido, new TipoEntregaFragment())
                            .commit();
                }else if(item.equals("TIPO DE PAGO")){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragPedido, new TipoPagoFragment())
                            .commit();
                }else if(item.equals("COMPROBANTE DE PAGO")){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragPedido, new ComprobantePagoFragment())
                            .commit();
                }
            }
        });

        fragPedido = (FrameLayout) findViewById(R.id.fragPedido);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragPedido, new TipoEntregaFragment())
                .disallowAddToBackStack()
                .commit();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i("RUN","Inicio");
//                long a = System.currentTimeMillis()+1;
//                long b = System.currentTimeMillis();
//                while(b != a+1500){
//                    b = System.currentTimeMillis();
//                    //Log.i("RUN",b+"-"+a+500);
//                }
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragPedido, new TipoPagoFragment())
//                        .commit();
//                Log.i("RUN","FIN");
//            }
//        }).start();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i("Uri",""+uri);
    }
}
