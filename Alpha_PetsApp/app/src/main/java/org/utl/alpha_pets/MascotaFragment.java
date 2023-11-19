package org.utl.alpha_pets;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.utl.alpha_pets.modelo.Mascota;
import org.utl.alpha_pets.modelo.Persona;

public class MascotaFragment extends Fragment {
    TextView lblNombreDueño;
    TextView lblNombreMascota;
    TextView lblEdadMascota;
    TextView lblRazaMascota;
    TextView lblTamanioMascota;
    TextView lblNota;
    Button btnAgregarMascota;
    Button btnEliminarCuenta;
    private static final int CODIGO_CONFIRMAR_ELIMINACION=1;
    private ActivityResultLauncher<Intent> confirmarEliminacionLauncher;

    public MascotaFragment(){

    }

    public static MascotaFragment newInstance(String param1, String param2){
        MascotaFragment fragment=new MascotaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_mascota_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        lblNombreDueño=view.findViewById(R.id.lblNombreDueño);
        lblNombreMascota=view.findViewById(R.id.lblNombreMascota);
        lblEdadMascota=view.findViewById(R.id.lblEdadMascota);
        lblRazaMascota=view.findViewById(R.id.lblRazaMascota);
        lblTamanioMascota=view.findViewById(R.id.lblTamanioMascota);
        lblNota=view.findViewById(R.id.lblNota);
        btnAgregarMascota=view.findViewById(R.id.btnAgregarMascota);
        btnEliminarCuenta=view.findViewById(R.id.btnEliminarCuenta);

        btnAgregarMascota.setOnClickListener(view1 -> {
            agregarMascota();
        });

        btnEliminarCuenta.setOnClickListener(view1 -> {
            eliminarCuenta();
        });

        llenarTxt();

        confirmarEliminacionLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode()==RESULT_OK){
                eliminarSharedPreferences();
                iniciarActividadLogin();
            } else if(result.getResultCode()==RESULT_CANCELED){

            }
        });
    }

    public void llenarTxt(){
        Mascota m=new Mascota();
        Persona p=new Persona();

        SharedPreferences sharedPreferences=requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String json=sharedPreferences.getString("mascotaJson", "");

        try{
            JSONObject jsonObject=new JSONObject(json);

            p.setIdPersona(jsonObject.getJSONObject("persona").getInt("idPersona"));
            p.setNombrePersona(jsonObject.getJSONObject("persona").getString("nombrePersona"));
            p.setUsuario(jsonObject.getJSONObject("persona").getString("usuario"));
            p.setContrasenia(jsonObject.getJSONObject("persona").getString("contrasenia"));

            m.setIdMascota(jsonObject.getInt("idMascota"));
            m.setNombreMascota(jsonObject.getString("nombreMascota"));
            m.setEdad(jsonObject.getInt("edad"));
            m.setRaza(jsonObject.getString("raza"));
            m.setTamanio(jsonObject.getString("tamanio"));
            m.setPersona(p);

            lblNombreDueño.setText(m.getPersona().getNombrePersona());
            lblNombreMascota.setText(m.getNombreMascota());
            lblEdadMascota.setText(m.getEdad() +" años");
            lblRazaMascota.setText(m.getRaza());
            lblTamanioMascota.setText(m.getTamanio());
            lblNota.setText("*¡RECUERDA MANTENER LIMPIO TU DISPENSADOR PARA ASI EVITAR QUE TU COMPAÑERO"+
                    " SUFRA DE ALGUNA ENFERMEDAD!*");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void agregarMascota(){
        Intent intent=new Intent(requireContext(), Registro.class);
        startActivity(intent);
    }

    public void eliminarCuenta(){
        Intent intent=new Intent(requireContext(), ConfirmarEliminacion.class);
        confirmarEliminacionLauncher.launch(intent);
    }

    private void eliminarSharedPreferences(){
        SharedPreferences sharedPreferences=requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    private void iniciarActividadLogin(){
        Intent intent=new Intent(requireContext(), MainActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }
}