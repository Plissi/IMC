package com.example.openproject;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private OnClickListener clickListenerCalcul = new OnClickListener() {
        @Override
        public void onClick(View view) {
            //récupération des nombres des editTexts

                valeurPoids = ParseFloat(poids.getText().toString());
                valeurTaille = ParseFloat(taille.getText().toString());

            if (valeurTaille==0 && valeurPoids ==0 || taille == null && poids ==null){
                Toast.makeText(getApplicationContext(), "Certains champs ont une valeur nulle...", Toast.LENGTH_SHORT).show();
            }else  if (valeurTaille==0 && valeurPoids !=0 || taille == null && poids !=null){
                Toast.makeText(getApplicationContext(), "La taille ne peut pas être nulle!", Toast.LENGTH_SHORT).show();
            }else  if (valeurTaille!=0 && valeurPoids ==0 || taille != null && poids ==null){
                Toast.makeText(getApplicationContext(), "La poids ne peut pas être nulle!", Toast.LENGTH_SHORT).show();
            } else{
                //Condition sur les boutons radio
                if (centimetre.isChecked()){
                    valeurTaille = valeurTaille / 100;
                    imc = calculImc(valeurPoids, valeurTaille);
                }
                else{
                    imc = calculImc(valeurPoids, valeurTaille);
                }
                resultat.setText("Ton imc est de " + imc);
            }

        }
    };

    private OnClickListener clickListenerRaz = new OnClickListener() {
        @Override
        public void onClick(View view) {
            poids.getText().clear();
            taille.getText().clear();
            Toast.makeText(getApplicationContext(), "Réinitialisation finie", Toast.LENGTH_SHORT).show();
        }
    };

    Button calcul = null;
    Button raz = null;
    RadioGroup radioGroup = null;
    RadioButton metre =null;
    RadioButton centimetre = null;
    EditText poids = null;
    EditText taille = null;
    CheckBox mega = null;
    TextView resultat = null;

    int idCheckedRadio = 0;
    float imc = 0;
    float valeurPoids = 0;
    float valeurTaille = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciation des boutons
        calcul = findViewById(R.id.calcul);
        raz = findViewById(R.id.raz);
        radioGroup = findViewById(R.id.group);
        metre = findViewById(R.id.radio1);
        centimetre = findViewById(R.id.radio2);

        //instanciation des editexts
        poids = findViewById(R.id.poids);
        taille = findViewById(R.id.taille);

        mega = findViewById(R.id.mega);

        resultat = findViewById(R.id.result);

        //récupération de l'id du bouton radio coché
        idCheckedRadio = radioGroup.getCheckedRadioButtonId();

        //ajout des listeners sur les boutons
          calcul.setOnClickListener(clickListenerCalcul);
          raz.setOnClickListener(clickListenerRaz);


    }

    public float calculImc(float poids, float taille) {
        float indiceMC = 0;
            indiceMC= poids / (taille * taille);
        return indiceMC;
    }

    float ParseFloat(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Float.parseFloat(strNumber);
            } catch(Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        else return 0;
    }
}
