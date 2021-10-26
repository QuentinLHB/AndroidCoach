package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controle.getInstance(this);
        creerMenu();
    }

    /**
     * Crée les listeners pointant vers les activités.
     */
    private void creerMenu() {
        ButtonListener(findViewById(R.id.imgMonIMG), CalculActivity.class);
        ButtonListener(findViewById(R.id.imgMonHisto), HistoActivity.class);
    }

    /**
     * Crée un listener sur un ImageButton, dont l'action ferme l'activity en cours et en ouvre une autre.
     *
     * @param imgBtn ImageButton sur laquelle appuyer.
     * @param classe Classe de l'Activity à ouvrir après le clic.
     */
    private void ButtonListener(ImageButton imgBtn, Class classe) {
        imgBtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}