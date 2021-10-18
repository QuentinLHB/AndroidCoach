package com.example.coach.modele;

import java.io.Serializable;

public class Profil implements Serializable {

    // constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus
    public static final String MAIGRE = "Trop maigre";
    public static final String GRAS = "Trop de graisse";
    public static final String NORMAL = "Normal";



    private Integer taille;
    private Integer poids;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;

    /**
     *
     * @return la taille entrée dans le profil.
     */
    public Integer getTaille() {
        return taille;
    }

    /**
     *
     * @return le poids entré dans le profil.
     */
    public Integer getPoids() {
        return poids;
    }

    /**
     *
     * @return l'âge entré dans le profil.
     */
    public Integer getAge() {
        return age;
    }

    /**
     *
     * @return le sexe entré dans le profil (0: Femme ; 1: Homme)
     */
    public Integer getSexe() {
        return sexe;
    }

    /**
     * Retourne l'Indice de Masse Graisseuse.
     * Formule du calcul : IMG = (1,2 * Poids/Taille²) + (0,23 * age) - (10,83 * S) - 5,4
     * Avec S = 0 (femme) ou 1 (homme)
     * @return Indice de Masse Graisseuse (IMG).
     */
    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    public Profil(Integer taille, Integer poids, Integer age, Integer sexe) {
        this.taille = taille;
        this.poids = poids;
        this.age = age;
        this.sexe = sexe;
        calculIMG();
        resultIMG();
    }

    /**
     * Calcule l'Indice de masse Graisseuse.
     * Formule du calcul : IMG = (1,2 * Poids/Taille²) + (0,23 * age) - (10,83 * S) - 5,4
     * Avec S = 0 (femme) ou 1 (homme)
     */
    private void calculIMG(){
        float tailleM = (float)taille/100;
        img = Double.valueOf(1.2 * (poids / Math.pow(tailleM, 2)) + 0.23 * age - 10.83 * sexe - 5.4).floatValue();
    }

    /**
     * Valorise le message lié à l'IMG.
     */
    private void resultIMG(){
        if(sexe == 0) message = messageIMG(minFemme, maxFemme);
        else message = messageIMG(minHomme, maxHomme);
    }

    /**
     * Retourne un message selon les seuils (dépendants du sexe).
     * @param seuilBas Seuil inférieur en dessous duquel la personne est trop maigre.
     * @param seuilHaut Seuil supérieur au dessus duquel une personne est trop grasse.
     * @return Le message à enregistrer.
     */
    private String messageIMG(int seuilBas, int seuilHaut){
        if(img < seuilBas) return MAIGRE;
        else if(img < seuilHaut) return NORMAL;
            else return GRAS;
    }
}
