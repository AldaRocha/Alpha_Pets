package org.utl.alpha_pets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.utl.alpha_pets.R;

public class TipsFragment extends Fragment{
    TextView txtAlimentoCalidad;
    TextView txtEducacionAnimal;
    TextView txtHigienePerro;
    TextView txtAtencionCuidadosVeterinarios;
    TextView txtAaspp;
    TextView txtLink1;
    TextView txtLink2;

    public TipsFragment(){

    }

    public static TipsFragment newInstance(String param1, String param2){
        TipsFragment fragment=new TipsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tips_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        txtAlimentoCalidad=view.findViewById(R.id.txtAlimentoCalidad);
        txtEducacionAnimal=view.findViewById(R.id.txtEducacionAnimal);
        txtHigienePerro=view.findViewById(R.id.txtHigienePerro);
        txtAtencionCuidadosVeterinarios=view.findViewById(R.id.txtAtencionCuidadosVeterinarios);
        txtAaspp=view.findViewById(R.id.txtAaspp);
        txtLink1=view.findViewById(R.id.txtLink1);
        txtLink2=view.findViewById(R.id.txtLink2);

        txtLink1.setOnClickListener(view1 -> {
            abrirLink1();
        });

        txtLink2.setOnClickListener(view1 -> {
            abrirLink2();
        });

        llenarTxt();
    }

    public void llenarTxt(){
        txtAlimentoCalidad.setText("Elegir una alimentación nutritivo y darles una buena hidratación"+
                " es clave para el bienestar de los perros. Hay que ofrecerles alimentos de calidad"+
                " en función de su etapa de vida, según si es cachorro, adulto o senior."+
                "\n"+
                "Si se trata de un cachorro o de un perro mayor habrá que adaptar su dieta a sus"+
                " requerimientos. Lo mismo sucede si la mascota tiene alguna intolerancia, patología"+
                " o necesidad alimentaria específica.");

        txtEducacionAnimal.setText("Enseñar a un perro es esencial para que se desarrolle satisfactoriamente."+
                " Hay que indicarle dónde hacer sus necesidades, en qué espacios, qué puede hacer y que"+
                " no, y guiarlo desde el principio para inculcarle un buen aprendizaje. Eso sí, siempre"+
                " desde el refuerzo positivo, evitando reñirle bruscamente o gritarle, ya que así solo"+
                " conseguiremos asustarle.");

        txtHigienePerro.setText("Si bien es cierto que hay mucha controversia sobre cada cuándo hay"+
                " que bañar a un perro, el promedio aproximado es una vez al mes. Asimismo, hay que"+
                " cepillarles diariamente, y cortarles el pelo y las uñas cuando sea necesario. También"+
                " hay que prestar atención a la higiene de sus oídos y ojos, para evitar la acumulación"+
                " de suciedad y de legañas.");

        txtAtencionCuidadosVeterinarios.setText("No siempre los cuidados del perro están a nuestro"+
                " alcance y acudir al veterinario es necesario en muchos casos para revisar su estado"+
                " de salud e intervenir con el tratamiento oportuno. Además, si tienes un perro deberás"+
                " visitar al veterinario para ponerle las correspondientes vacunas, el chip y determinar"+
                " un plan de desparasitación adecuado al animal.");

        txtAaspp.setText("-Las bolsas de comida seca para perros están impresas con una fecha de caducidad"+
                " para que sepas cuánto tiempo puedes guardarlas. Sin embargo, muchos dueños de perros no se"+
                " dan cuenta de que esta fecha se vuelve inexacta una vez que se abre la bolsa. Se recomienda"+
                " que los dueños usen la croqueta seca solo un mes después de abrirla si se ha almacenado"+
                " correctamente."+
                "\n"+
                "Una vez que abres una bolsa de comida para perros, su frescura queda expuesta a los elementos."+
                " Los tres factores que afectan el valor nutricional y la calidad del alimento seco de un"+
                " perro una vez abierto son el aire, la humedad y las altas temperaturas. Para ayudar a"+
                " combatir estas fuerzas, aquí hay algunos consejos para el almacenamiento de alimentos"+
                " secos para perros."+
                "\n"+
                "1.-Selle su bolsa:"+
                "\n"+
                "Para sellar la bolsa de comida para perros, puede enrollar los costados y usar un clip para"+
                " cerrar la bolsa. Para una protección adicional."+
                "\n"+
                "2.-Usar recipientes herméticos para almacenar alimentos para perros:"+
                "\n"+
                "Cuando se trata del tipo de recipientes de comida para perros que debe usar, los"+
                " recipientes de plástico o acero inoxidable están bien para almacenar croquetas"+
                " siempre que sean herméticos.");
    }

    public void abrirLink1(){
        Uri webpage=Uri.parse("https://postgradoveterinaria.com/cuidados-perro-recomendaciones-basicas/");
        Intent intent=new Intent(Intent.ACTION_VIEW, webpage);
        if(intent.resolveActivity(requireContext().getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void abrirLink2(){
        Uri webpage=Uri.parse("https://be.chewy.com/best-practices-for-proper-dog-food-storage/");
        Intent intent=new Intent(Intent.ACTION_VIEW, webpage);
        if(intent.resolveActivity(requireContext().getPackageManager())!=null){
            startActivity(intent);
        }
    }
}