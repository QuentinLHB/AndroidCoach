package com.example.coach.modele;

import junit.framework.TestCase;

public class ProfilTest extends TestCase {

    // création d’un profil : femme de 67kg, 1m65, 35 ans
    private Profil profil = new Profil(165, 67, 35, 0);
    // résultat de l’img correspondant
    private float img = (float)32.2 ;
    // message correspondant
    private String message = "Trop de graisse" ;

    public void testGetImg(){
        assertEquals(img, profil.getImg(), 0.1f);
    }

    public void testGetMessage(){
        assertEquals(message, profil.getMessage());
    }

}