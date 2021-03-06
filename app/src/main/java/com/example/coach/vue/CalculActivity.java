package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;

public class CalculActivity extends AppCompatActivity {

    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private ImageView lblImg;
    private TextView lblMessage;
    private Button btnCalcul;
    private Controle controle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();

    }

    /**
     * Récupère les objets dans l'activity pour y accéder dans la Vue.
     */
    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        lblImg = (ImageView) findViewById(R.id.lblIMG);
        lblMessage = (TextView) findViewById(R.id.lblMessage);
        btnCalcul = (Button) findViewById(R.id.btnCalc);
        controle = Controle.getInstance(this);
        calculListener();
        recupProfil();
        retourListener();

    }

    /**
     * Crée l'event listener du bouton Retour.
     */
    private void retourListener() {
        ImageButton imgRetour = findViewById(R.id.imgRetourDeCalcul);
        imgRetour.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }


    /**
     * Récupère les informations du profil enregistré dans le contrôleur et l'affiche.
     */
    private void recupProfil() {
//        controle.getSerial(this); Remplacé par SQLite
        remplireSiNonVide(controle.getPoids(), txtPoids);
        remplireSiNonVide(controle.getTaille(), txtTaille);
        remplireSiNonVide(controle.getAge(), txtAge);
        if (controle.getSexe() == 0) rdFemme.setChecked(true);
//        btnCalcul.performClick();
        controle.setProfil(null);
    }

    /**
     * Remplit un widget avec une valeur numérique entière non-nulle.
     * @param valeur Entier à afficher
     * @param widget Widget dans lequel afficher la valeur.
     */
    private void remplireSiNonVide(int valeur, EditText widget) {
        if (valeur != 0) widget.setText(String.valueOf(valeur));
    }

    /**
     * Crée l'event listener sur le bouton effectuant le calcul.
     */
    private void calculListener() {
        btnCalcul.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int poids = 0;
                int taille = 0;
                int age = 0;

                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch (Exception e) {
                }

                int sexe = 0;
                if (rdHomme.isChecked()) sexe = 1;

                if (poids == 0 || taille == 0 || age == 0)
                    Toast.makeText(CalculActivity.this, "Merci de remplir tous les champs.", Toast.LENGTH_SHORT).show();
                else
                    affichResult(taille, poids, age, sexe);
            }
        });
    }

    /**
     * Crée le profil en fonction des infos remplies et affiche le résultat du calcul.
     * @param taille Taille enregistrée.
     * @param poids Poids enregistré.
     * @param age Âge enregistré.
     * @param sexe Sexe enregistré (0: femme, 1:homme).
     */
    private void affichResult(Integer taille, Integer poids, Integer age, Integer sexe) {
        controle.creerProfil(taille, poids, age, sexe, this);
        String msgLbl = controle.getImg2Decimal() + " : ";
        String msgImg = controle.getMessage();
        int color;
        int imgResource;
        switch (msgImg) {
            case Profil.MAIGRE:
                msgLbl += "IMG trop faible.";
                imgResource = R.drawable.maigre;
                color = Color.RED;
                break;
            case Profil.GRAS:
                msgLbl += "IMG trop élevée.";
                imgResource = R.drawable.graisse;
                color = Color.RED;
                break;
            default:
                msgLbl += "IMG normale";
                imgResource = R.drawable.normal;
                color = Color.GREEN;
                break;
        }
        lblImg.setImageResource(imgResource);
        lblMessage.setText(msgLbl);
        lblMessage.setTextColor(color);
    }
}