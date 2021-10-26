package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.MesOutils;
import com.example.coach.outils.Serializer;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

public final class Controle {

    private static Controle instance;


    private Profil profil;
    private ArrayList<Profil> lesProfils;
    private String fileName = "saveprofil";
    private static AccesLocal accesLocal;
//    private static AccesDistant accesDistant;


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
            Controle.accesLocal = new AccesLocal(context);
//        instance.profil = accesLocal.recupDernier();
            instance.lesProfils = accesLocal.recupProfils();
//        accesDistant = new AccesDistant() ;
//        accesDistant.envoi("dernier", new JSONArray());
        }
        return Controle.instance;
    }

    /**
     * Crée le profil de l'utilisateur et l'ajout dans la BDD.
     * @param taille Taille entrée.
     * @param poids Poids entré.
     * @param age Âge entré.
     * @param sexe Sexe entré (0: Femme ou 1: Homme)
     */
    public void creerProfil(Integer taille, Integer poids, Integer age, Integer sexe, Context context){
        profil = new Profil(taille, poids, age, sexe, new Date());
//        Serializer.serialize(fileName, profil, context);
         accesLocal.ajout(profil);
         lesProfils.add(profil);

//        accesDistant.envoi("enreg", profil.convertToJSONArray());
    }

    public void supprProfil(Profil profil){
        accesLocal.supprProfil(profil);
        lesProfils.remove(profil);

    }

    /**
     *
     * @return L'Indice de Masse Graisseuse calculée à partir des données de l'application.
     */
    public float getImg(){
        if(profil != null) return profil.getImg();
        return 0;
    }

    public String getImg2Decimal(){
        return MesOutils.format2Decimal(getImg());
    }

    public String getMessage(){
        if(profil != null) return profil.getMessage();
        return "";
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

    public void setLesProfils(ArrayList<Profil> lesProfils) {
        this.lesProfils = lesProfils;
    }

    public void setProfil(Profil profil){
        this.profil = profil;
    }

    /**
     *
     * @return La liste des profils récupérés dans la BDD locale.
     */
    public ArrayList<Profil> getLesProfils() {
        return lesProfils;
    }

//    public void getSerial(Context context) {
//        profil = (Profil) Serializer.deSerialize(fileName, context);
//    }


}
