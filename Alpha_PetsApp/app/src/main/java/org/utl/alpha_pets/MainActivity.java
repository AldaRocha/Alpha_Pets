package org.utl.alpha_pets;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.utl.alpha_pets.modelo.Mascota;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity{
    EditText txtUsuario;
    EditText txtContrasenia;
    Button btnLogin;
    Button btnRegistrar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario=findViewById(R.id.txtUsuario);
        txtContrasenia=findViewById(R.id.txtContrasenia);
        btnLogin=findViewById(R.id.btnLogin);
        btnRegistrar=findViewById(R.id.btnRegistrar);
    }

    public void iniciarSesion(View view){
        Mascota m=new Mascota();

        m.getPersona().setUsuario(String.valueOf(txtUsuario.getText()));
        m.getPersona().setContrasenia(String.valueOf(txtContrasenia.getText()));
        try{
            InputStream is=getAssets().open("data.json");
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();

            String js=new String(buffer, "UTF-8");
            JSONArray jsonArray=new JSONArray(js);

            boolean inicioSesionExitoso=false;
            JSONObject mascotaJson=null;

            for(int i=0; i<jsonArray.length(); i++){
                 JSONObject mascota=jsonArray.getJSONObject(i);
                 JSONObject personaJson=mascota.getJSONObject("persona");

                 String usuario=personaJson.getString("usuario");
                 String contrasenia=personaJson.getString("contrasenia");

                 if(usuario.equals(m.getPersona().getUsuario()) && contrasenia.equals(m.getPersona().getContrasenia())){
                     inicioSesionExitoso=true;
                     mascotaJson=mascota;
                     break;
                 }
            }
            if(inicioSesionExitoso){
                SharedPreferences sharedPreferences=getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("mascotaJson", mascotaJson.toString());
                editor.apply();
                Toast.makeText(this, "Bienvenido "+m.getPersona().getUsuario(), Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, HomeFragment.class);
                startActivity(intent);
            } else{
                Toast.makeText(this, "Credenciales incorrectas, intente de nuevo", Toast.LENGTH_LONG).show();
                txtUsuario.setText("");
                txtContrasenia.setText("");
            }
        } catch(IOException | JSONException e){
            e.printStackTrace();
        }
    }

    public void registrar(View view){
        Intent ventanaRegistro=new Intent(this, Registro.class);
        startActivity(ventanaRegistro);
    }
}