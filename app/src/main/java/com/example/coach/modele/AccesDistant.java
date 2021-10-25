package com.example.coach.modele;

import android.util.Log;

import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;

import org.json.JSONArray;

public class AccesDistant implements AsyncResponse {

    private static final String SERVADDR = "http://192.168.56.1/coach/serveurcoach.php";

    public AccesDistant() {
        super();
    }

    @Override
    public void processFinish(String output) {
        Log.d("serveur", "************" + output);
        String[] message = output.split("%");
        if (message.length == 2) {
            switch (message[1]) {
                case "enreg":
                    Log.d("enreg", output);
                    break;
                case "dernier":
                    Log.d("dernier", output);
                    break;
                case "Erreur ! ":
                    Log.d("Erreur", output);
                    break;
                default:
                    Log.d("Unexpected", output);
                    break;
            }
        }
    }

    public void envoi(String operation, JSONArray lesDonneesJSON) {
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVADDR);


    }

}

