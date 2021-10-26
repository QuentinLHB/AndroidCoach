package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;

import java.util.ArrayList;
import java.util.Collections;

public class HistoActivity extends AppCompatActivity {
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        init();

    }

    /**
     * Initialise l'activité.
     */
    private void init() {
        controle = Controle.getInstance(this);
        initListeners();
        creeListe();

    }

    /**
     * Initialise les listeners sur les objets.
     */
    private void initListeners() {
        ImageButton imgRetour = findViewById(R.id.imgRetourDeHisto);
        imgRetour.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    /**
     * Crée la liste des mesures enregistrées en utilisant l'Adapter
     */
    private void creeListe() {
        ArrayList<Profil> lesProfils = controle.getLesProfils();

        if (lesProfils != null) {
            Collections.sort(lesProfils, Collections.<Profil>reverseOrder());
            ListView lstHisto = (ListView) findViewById(R.id.lstHisto);
            HistoListAdapter histoAdapter = new HistoListAdapter(HistoActivity.this, lesProfils);
            lstHisto.setAdapter(histoAdapter);
        }
    }

    /**
     * Envoie le profil sélectionné à la page de calcul d'activité.
     * @param profil Profil à envoyer.
     */
    public void afficheProfil(Profil profil) {
        controle.setProfil(profil);
        Intent intent = new Intent(HistoActivity.this, CalculActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}