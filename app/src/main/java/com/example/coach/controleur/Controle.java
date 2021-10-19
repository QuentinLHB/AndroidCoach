package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

import java.util.Date;

public final class Controle {

    private static Controle instance;
    private Profil profil;
    private String fileName = "saveprofil";
    private static AccesLocal accesLocal;


    /**
     * Crée l'instance unique de la classe Singleton du contrôleur.
     */
    private Controle(){
        super();
    }

    /**
     * Récupère l'instance unique du contrôleur.
     * @return L'objet Contrôleur unique.
     */
    public static Controle getInstance(Context context){
        if(instance == null){
            Controle.instance = new Controle();
//            instance.getSerial(context);
        }
        Controle.accesLocal = new AccesLocal(context);
        instance.profil = accesLocal.recupDernier();

        return Controle.instance;
    }

    /**
     * Crée le profil de l'utilisateur.
     * @param taille Taille entrée.
     * @param poids Poids entré.
     * @param age Âge entré.
     * @param sexe Sexe entré (0: Femme ou 1: Homme)
     */
    public void creerProfil(Integer taille, Integer poids, Integer age, Integer sexe, Context context){
        profil = new Profil(taille, poids, age, sexe, new Date());
//        Serializer.serialize(fileName, profil, context);
         accesLocal.ajout(profil);
    }

    /**
     *
     * @return L'Indice de Masse Graisseuse calculée à partir des données de l'application.
     */
    public float getImg(){
        if(profil != null) return profil.getImg();
        return 0;
    }

    public String getMessage(){
        if(profil != null) return profil.getMessage();
        return null;
    }

    public int getPoids(){
        if(profil != null) return profil.getPoids();
        return 0;
    }

    public int getTaille(){
        if(profil != null) return profil.getTaille();
        return 0;
    }

    public int getAge(){
        if(profil != null) return profil.getAge();
        return 0;
    }

    public int getSexe(){
        if(profil != null) return profil.getSexe();
        return 1;
    }

    public void getSerial(Context context) {
        profil = (Profil) Serializer.deSerialize(fileName, context);
    }


}
