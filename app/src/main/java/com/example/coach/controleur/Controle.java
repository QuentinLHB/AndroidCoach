package com.example.coach.controleur;

import com.example.coach.modele.Profil;

public final class Controle {

    private static Controle instance;
    private Profil profil;

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
    public static Controle getInstance(){
        if(instance == null){
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    /**
     * Crée le profil de l'utilisateur.
     * @param taille Taille entrée.
     * @param poids Poids entré.
     * @param age Âge entré.
     * @param sexe Sexe entré (0: Femme ou 1: Homme)
     */
    public void creerProfil(Integer taille, Integer poids, Integer age, Integer sexe){
        profil = new Profil(taille, poids, age, sexe);
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
        return "";
    }

}
