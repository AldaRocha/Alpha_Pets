package org.utl.alpha_pets;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.utl.alpha_pets.modelo.Mascota;
import org.utl.alpha_pets.modelo.Persona;

import java.io.IOException;
import java.io.InputStream;

public class Registro extends AppCompatActivity{
    EditText txtNombreDueño;
    EditText txtRegistrarUsuario;
    EditText txtRegistrarContrasenia;
    EditText txtNombreMascota;
    EditText txtEdadMascota;
    EditText txtRazaMascota;
    RadioGroup radioGroup;
    RadioButton radioChico;
    RadioButton radioMediano;
    RadioButton radioGrande;
    Button btnRegistroIngreso;
    Button btnRegresar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtNombreDueño=findViewById(R.id.txtNombreDueño);
        txtRegistrarUsuario=findViewById(R.id.txtRegistrarUsuario);
        txtRegistrarContrasenia=findViewById(R.id.txtRegistrarContrasenia);
        txtNombreMascota=findViewById(R.id.txtNombreMascota);
        txtEdadMascota=findViewById(R.id.txtEdadMascota);
        txtRazaMascota=findViewById(R.id.txtRazaMascota);
        radioGroup=findViewById(R.id.radioGroup);
        radioChico=findViewById(R.id.radioChico);
        radioMediano=findViewById(R.id.radioMediano);
        radioGrande=findViewById(R.id.radioGrande);
        btnRegistroIngreso=findViewById(R.id.btnRegistroIngreso);
        btnRegresar=findViewById(R.id.btnRegresar);
    }

    public void registrarDatos(View view){
        Mascota m=new Mascota();

        try{
            InputStream is=getAssets().open("data.json");
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();

            String json=new String(buffer, "UTF-8");
            JSONArray jsonArray=new JSONArray(json);

            Persona p=m.getPersona();
            p.setIdPersona(jsonArray.length()+1);
            p.setNombrePersona(txtNombreDueño.getText().toString());
            p.setUsuario(txtRegistrarUsuario.getText().toString());
            p.setContrasenia(txtRegistrarContrasenia.getText().toString());
            m.setIdMascota(jsonArray.length()+1);
            m.setNombreMascota(txtNombreMascota.getText().toString());
            m.setEdad(Integer.parseInt(txtEdadMascota.getText().toString()));
            m.setRaza(txtRazaMascota.getText().toString());
            if(radioChico.isChecked()==true){
                m.setTamanio("Chico");
            } else if(radioMediano.isChecked()==true){
                m.setTamanio("Mediano");
            } else if(radioGrande.isChecked()==true){
                m.setTamanio("Grande");
            } else{
                m.setTamanio("No seleccionaste el tamaño de la mascota");
            }

            JSONObject nuevoRegistro=new JSONObject();
            nuevoRegistro.put("idMascota", m.getIdMascota());
            nuevoRegistro.put("nombreMascota", m.getNombreMascota());
            nuevoRegistro.put("edad", m.getEdad());
            nuevoRegistro.put("raza", m.getRaza());
            nuevoRegistro.put("tamanio", m.getTamanio());
            JSONObject persona=new JSONObject();
            persona.put("idPersona", m.getPersona().getIdPersona());
            persona.put("nombrePersona", m.getPersona().getNombrePersona());
            persona.put("usuario", m.getPersona().getUsuario());
            persona.put("contrasenia", m.getPersona().getContrasenia());
            nuevoRegistro.put("persona", persona);

            jsonArray.put(nuevoRegistro);

            SharedPreferences sharedPreferences=getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("mascotaJson", nuevoRegistro.toString());
            editor.apply();

            Toast.makeText(this, "Datos guardados con exito.", Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(Registro.this, HomeFragment.class);
            startActivity(intent);
        } catch (IOException | JSONException e){
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar los datos.", Toast.LENGTH_SHORT).show();
        }
    }

    public void regresar(View view){
        finish();
    }
}