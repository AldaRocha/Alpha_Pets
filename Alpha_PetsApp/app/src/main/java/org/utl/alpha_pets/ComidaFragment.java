package org.utl.alpha_pets;

import static android.content.Intent.getIntent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ComidaFragment extends Fragment {
    ProgressBar progressBarAlimento;
    ProgressBar progressBarAgua;
    TextView lblCapAlimento;
    TextView lblCapAgua;
    ImageButton btnBT;
    Button btnDesconectar;
    ImageButton btnRefrescar;
    Handler bluetoothIn;
    final int handlerState=0;
    TextView Idmensaje;
    private BluetoothAdapter btAdapter=null;
    private BluetoothSocket btSocket=null;
    private StringBuilder DataStringIN=new StringBuilder();
    private ConnectedThread MyConexionBT;
    private static final UUID BTMODULEUUID=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address=null;
    private int nivelAgua=0;
    private int nivelComida=0;

    public ComidaFragment(){

    }

    public static ComidaFragment newInstance(String param1, String param2){
        ComidaFragment fragment=new ComidaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("WrongThread")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_comida_fragment, container, false);
        progressBarAlimento=view.findViewById(R.id.progressBarAlimento);
        progressBarAgua=view.findViewById(R.id.progressBarAgua);
        lblCapAlimento=view.findViewById(R.id.lblCapAlimento);
        lblCapAgua=view.findViewById(R.id.lblCapAgua);
        btnBT=view.findViewById(R.id.btnBT);
        btnDesconectar=view.findViewById(R.id.btnDesconectar);
        btnRefrescar=view.findViewById(R.id.btnRefrescar);

        bluetoothIn=new Handler(){
            public void handleMessage(@NonNull Message msg){
                super.handleMessage(msg);
                if(msg.what == handlerState){
                    String readMessage=(String) msg.obj;
                    System.out.println("\n\n\n\n\n\n readMessage = " + readMessage+"\n\n\n\n\n\n");

                    updateWaterLevel(readMessage);

                    updateFoodLevel(readMessage);
                    DataStringIN.append(readMessage);
                }
            }
        };

        btnBT.setOnClickListener(view1 -> {
            Intent intentBlue=new Intent(requireContext(), Dispositivos_bt.class);
            startActivity(intentBlue);
        });

        btAdapter=BluetoothAdapter.getDefaultAdapter();
        verificarEstadoBt();

        btnRefrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyConexionBT.write("1");
            }
        });

        btnDesconectar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(btSocket != null){
                    try{
                        btSocket.close();
                    } catch(IOException e){
                        Toast.makeText(requireContext(), "Error al cerrar la conexión bluetooth", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    private void updateWaterLevel(String cadena){
        try{
            System.out.println("Hola desde update water level: "+cadena);
            if(cadena.length()>4){
                System.out.println("La cadena es grande, agua");
                int diagonal=cadena.indexOf("/");
                System.out.println("diagonal = " + diagonal);
                nivelAgua=Integer.parseInt(cadena.substring(1, diagonal));
                System.out.println("nivelAgua = " + nivelAgua);
                progressBarAgua.setProgress(nivelAgua);
                lblCapAgua.setText(nivelAgua+"%");
            }else{
                System.out.println("Si funciona pero no se por que manda este error");
            }
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("No se por que da error pero si se actualiza la comida");
        }
    }

    private void updateFoodLevel(String cadena){
        try{
            System.out.println("Hola desde update food level: "+cadena);
            if(cadena.length()>4){
                System.out.println("La cadena es grande, comida");
                int diagonal=cadena.indexOf("/");
                System.out.println("diagonal = " + diagonal);
                int finalCadena=cadena.trim().length();
                System.out.println("finalCadena = " + finalCadena);
                nivelComida=Integer.parseInt(cadena.substring(diagonal+3, finalCadena));
                System.out.println("nivelComida = " + nivelComida);
                progressBarAlimento.setProgress(nivelComida);
                lblCapAlimento.setText(nivelComida+"%");
            }else{
                System.out.println("Si funciona pero no se por que manda este error");
            }
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("No se por que da error pero si se actualiza la comida");
        }
    }

    @SuppressLint("MissingPermission")
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException{
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onResume(){
        super.onResume();
        String address=getActivity().getIntent().getStringExtra(Dispositivos_bt.EXTRA_DEVICE_ADDRESS);
        if(address != null){
            BluetoothDevice device=btAdapter.getRemoteDevice(address);
            try{
                btSocket=createBluetoothSocket(device);
            } catch(IOException e){
                Toast.makeText(requireContext(), "Error al crear el socket Bluetooth", Toast.LENGTH_SHORT).show();
            }
            try{
                btSocket.connect();
                Toast.makeText(requireContext(), "Conectado al dispositivo Bluetooth", Toast.LENGTH_SHORT).show();
            } catch(IOException e){
                try{
                    btSocket.close();
                } catch(IOException e2){
                    Toast.makeText(requireContext(), "Error al cerrar la conexion Bluetooth", Toast.LENGTH_SHORT).show();
                }
            }
            MyConexionBT=new ConnectedThread(btSocket);
            MyConexionBT.start();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @SuppressLint("MissingPermission")
    private void verificarEstadoBt(){
        if(btAdapter == null){
            Toast.makeText(requireContext(), "El dispositivo no soporta Bluetooth", Toast.LENGTH_LONG).show();
        } else{
            if(btAdapter.isEnabled()){
            } else{
                Intent enableBtIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    private class ConnectedThread extends Thread{
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket){
            InputStream tmpIn=null;
            OutputStream tmpOut=null;
            try{
                tmpIn=socket.getInputStream();
                tmpOut=socket.getOutputStream();
            } catch(IOException e){
                Toast.makeText(requireContext(), "Error al obtener los streams de entrada y salida", Toast.LENGTH_SHORT).show();
            }
            mmInStream=tmpIn;
            mmOutStream=tmpOut;
        }

        public void run(){
            byte[] buffer=new byte[256];
            int bytes;
            while(true){
                try{
                    bytes=mmInStream.read(buffer);
                    String readMessage=new String(buffer, 0, bytes);
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch(IOException e){
                    break;
                }
            }
        }

        public void write(String input){
            try{
                mmOutStream.write(input.getBytes());
            } catch(IOException e){
                Toast.makeText(requireContext(), "Error al enviar datos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}