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
 * {@link ComprobantePagoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComprobantePagoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComprobantePagoFragment extends Fragment {

    LinearLayout lyRUC;
    LinearLayout lyRS;

    RadioButton rdBoleta;
    RadioButton rdFactura;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ComprobantePagoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComprobantePagoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComprobantePagoFragment newInstance(String param1, String param2) {
        ComprobantePagoFragment fragment = new ComprobantePagoFragment();
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
        return inflater.inflate(R.layout.fragment_comprobante_pago, container, false);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        lyRUC = (LinearLayout) getView().findViewById(R.id.lyRUC);
        lyRS = (LinearLayout) getView().findViewById(R.id.lyRS);

        View.OnClickListener ocl = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if(id == R.id.rdBoleta){
                    lyRUC.setVisibility(View.INVISIBLE);
                    lyRS.setVisibility(View.INVISIBLE);
                    RegistrarPedidoActivity.tipoComprobante = 0;
                }else if(id == R.id.rdFactura){
                    lyRUC.setVisibility(View.VISIBLE);
                    lyRS.setVisibility(View.VISIBLE);
                    RegistrarPedidoActivity.tipoComprobante = 1;
                }
            }
        };

        rdBoleta = (RadioButton) getView().findViewById(R.id.rdBoleta);
        rdFactura = (RadioButton) getView().findViewById(R.id.rdFactura);

        rdBoleta.setOnClickListener(ocl);
        rdFactura.setOnClickListener(ocl);

        if(RegistrarPedidoActivity.tipoComprobante == 1){
            rdFactura.setChecked(true);
            lyRUC.setVisibility(View.VISIBLE);
            lyRS.setVisibility(View.VISIBLE);
        }else{
            rdBoleta.setChecked(true);
            lyRUC.setVisibility(View.INVISIBLE);
            lyRS.setVisibility(View.INVISIBLE);
        }

        super.onViewCreated(view, savedInstanceState);
    }
}
