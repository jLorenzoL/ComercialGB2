package com.upc.gmt.pedido;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.upc.gmt.comercialgb.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TipoPagoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TipoPagoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TipoPagoFragment extends Fragment {

    LinearLayout lyBanco;
    LinearLayout lyCuentaBancaria;
    LinearLayout lyNumeroTarjeta;
    LinearLayout lyNombre;
    LinearLayout lyApellido;
    LinearLayout lyFechaCaducidad;
    LinearLayout lyCSV;

    RadioButton rdConsignacion;
    RadioButton rdEfectivo;
    RadioButton rdTarjeta;
    RadioButton rdTransferencia;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TipoPagoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TipoPagoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TipoPagoFragment newInstance(String param1, String param2) {
        TipoPagoFragment fragment = new TipoPagoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tipo_pago, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        lyBanco = (LinearLayout) getView().findViewById(R.id.lyBanco);
        lyCuentaBancaria = (LinearLayout) getView().findViewById(R.id.lyCuentaBancaria);
        lyNumeroTarjeta = (LinearLayout) getView().findViewById(R.id.lyNumeroTarjeta);
        lyNombre = (LinearLayout) getView().findViewById(R.id.lyNombre);
        lyApellido = (LinearLayout) getView().findViewById(R.id.lyApellido);
        lyFechaCaducidad = (LinearLayout) getView().findViewById(R.id.lyFechaCaducidad);
        lyCSV = (LinearLayout) getView().findViewById(R.id.lyCSV);

        View.OnClickListener ocl = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                rdConsignacion.setChecked(false);
                rdEfectivo.setChecked(false);
                rdTarjeta.setChecked(false);
                rdTransferencia.setChecked(false);
                int id = v.getId();
//                Log.i("ID",""+id);
                if(id == R.id.rdConsignacion){
                    rdConsignacion.setChecked(true);
                    RegistrarPedidoActivity.tipoPago = 2;
                    lyBanco.setVisibility(View.INVISIBLE);
                    lyCuentaBancaria.setVisibility(View.INVISIBLE);
                    lyNumeroTarjeta.setVisibility(View.INVISIBLE);
                    lyNombre.setVisibility(View.INVISIBLE);
                    lyApellido.setVisibility(View.INVISIBLE);
                    lyFechaCaducidad.setVisibility(View.INVISIBLE);
                    lyCSV.setVisibility(View.INVISIBLE);
                }else if(id == R.id.rdEfectivo){
                    rdEfectivo.setChecked(true);
                    RegistrarPedidoActivity.tipoPago = 1;
                    lyBanco.setVisibility(View.INVISIBLE);
                    lyCuentaBancaria.setVisibility(View.INVISIBLE);
                    lyNumeroTarjeta.setVisibility(View.INVISIBLE);
                    lyNombre.setVisibility(View.INVISIBLE);
                    lyApellido.setVisibility(View.INVISIBLE);
                    lyFechaCaducidad.setVisibility(View.INVISIBLE);
                    lyCSV.setVisibility(View.INVISIBLE);
                }else if(id == R.id.rdTarjeta){
                    rdTarjeta.setChecked(true);
                    RegistrarPedidoActivity.tipoPago = 3;
                    lyBanco.setVisibility(View.INVISIBLE);
                    lyCuentaBancaria.setVisibility(View.INVISIBLE);
                    lyNumeroTarjeta.setVisibility(View.VISIBLE);
                    lyNombre.setVisibility(View.VISIBLE);
                    lyApellido.setVisibility(View.VISIBLE);
                    lyFechaCaducidad.setVisibility(View.VISIBLE);
                    lyCSV.setVisibility(View.VISIBLE);
                }else if(id == R.id.rdTransferencia){
                    rdTransferencia.setChecked(true);
                    RegistrarPedidoActivity.tipoPago = 4;
                    lyBanco.setVisibility(View.VISIBLE);
                    lyCuentaBancaria.setVisibility(View.VISIBLE);
                    lyNumeroTarjeta.setVisibility(View.INVISIBLE);
                    lyNombre.setVisibility(View.INVISIBLE);
                    lyApellido.setVisibility(View.INVISIBLE);
                    lyFechaCaducidad.setVisibility(View.INVISIBLE);
                    lyCSV.setVisibility(View.INVISIBLE);
                }
            }
        };
        rdConsignacion = (RadioButton) getView().findViewById(R.id.rdConsignacion);
        rdEfectivo = (RadioButton) getView().findViewById(R.id.rdEfectivo);
        rdTarjeta = (RadioButton) getView().findViewById(R.id.rdTarjeta);
        rdTransferencia = (RadioButton) getView().findViewById(R.id.rdTransferencia);

        rdConsignacion.setOnClickListener(ocl);
        rdEfectivo.setOnClickListener(ocl);
        rdTarjeta.setOnClickListener(ocl);
        rdTransferencia.setOnClickListener(ocl);

        cargarPantalla();

        super.onViewCreated(view, savedInstanceState);
    }

    private void cargarPantalla() {
        int id = RegistrarPedidoActivity.tipoPago;
        if(id == 2){
            rdConsignacion.setChecked(true);
            lyBanco.setVisibility(View.INVISIBLE);
            lyCuentaBancaria.setVisibility(View.INVISIBLE);
            lyNumeroTarjeta.setVisibility(View.INVISIBLE);
            lyNombre.setVisibility(View.INVISIBLE);
            lyApellido.setVisibility(View.INVISIBLE);
            lyFechaCaducidad.setVisibility(View.INVISIBLE);
            lyCSV.setVisibility(View.INVISIBLE);
        }else if(id == 1){
            rdEfectivo.setChecked(true);
            lyBanco.setVisibility(View.INVISIBLE);
            lyCuentaBancaria.setVisibility(View.INVISIBLE);
            lyNumeroTarjeta.setVisibility(View.INVISIBLE);
            lyNombre.setVisibility(View.INVISIBLE);
            lyApellido.setVisibility(View.INVISIBLE);
            lyFechaCaducidad.setVisibility(View.INVISIBLE);
            lyCSV.setVisibility(View.INVISIBLE);
        }else if(id == 3){
            rdTarjeta.setChecked(true);
            lyBanco.setVisibility(View.INVISIBLE);
            lyCuentaBancaria.setVisibility(View.INVISIBLE);
            lyNumeroTarjeta.setVisibility(View.VISIBLE);
            lyNombre.setVisibility(View.VISIBLE);
            lyApellido.setVisibility(View.VISIBLE);
            lyFechaCaducidad.setVisibility(View.VISIBLE);
            lyCSV.setVisibility(View.VISIBLE);
        }else if(id == 4){
            rdTransferencia.setChecked(true);
            lyBanco.setVisibility(View.VISIBLE);
            lyCuentaBancaria.setVisibility(View.VISIBLE);
            lyNumeroTarjeta.setVisibility(View.INVISIBLE);
            lyNombre.setVisibility(View.INVISIBLE);
            lyApellido.setVisibility(View.INVISIBLE);
            lyFechaCaducidad.setVisibility(View.INVISIBLE);
            lyCSV.setVisibility(View.INVISIBLE);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
