package com.example.coach.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coach.controleur.Controle;
import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccesLocal {
    private String nomBase = "bdCoach.sqlite";
    private int versionBase = 1;
    MySQLiteOpenHelper accesBD;
    SQLiteDatabase bd;

    /**
     * Constructeur de l'accès à la BDD locale.
     * Crée ou ouvre l'accès.
     * @param context
     */
    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);

    }

    /**
     * Ajout d'un profil à la BDD locale.
     * @param profil Profil à ajouter à la BDD locale.
     */
    public void ajout(Profil profil){
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("poids", profil.getPoids());
        values.put("taille", profil.getTaille());
        values.put("age", profil.getAge());
        values.put("sexe", profil.getSexe());
        values.put("datemesure", profil.getDateMesure().toString());
        bd.insert("profil", null, values);
        bd.close();
    }

    /**
     * Récupère la dernière entrée dans la base de données SQLite.
     * @return
     */
    public Profil recupDernier(){
        bd = accesBD.getReadableDatabase();
        String req = "SELECT taille, poids, age, sexe, datemesure FROM profil";
        Profil profil = null;
        Cursor cursor = bd.rawQuery(req, null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            profil = new Profil(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    MesOutils.convertStringToDate(cursor.getString(4))
            );

        }
        cursor.close();
        return profil;
    }

    /**
     * Récupère tous les profils enregistrés dans la BDD locale.
     * @return Une Liste d'objets Profil, contenant tous les profils de la BDD locale.
     */
    public ArrayList<Profil> recupProfils(){
        bd = accesBD.getReadableDatabase();
        String req = "SELECT taille, poids, age, sexe, datemesure FROM profil";
        ArrayList<Profil> lesProfils = new ArrayList<>();
        Cursor cursor = bd.rawQuery(req, null);
        if(!cursor.moveToFirst()) return lesProfils;
        while (!cursor.isAfterLast()){
            lesProfils.add(new Profil(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    MesOutils.convertStringToDate(cursor.getString(4))
                    )
            );
            cursor.moveToNext();
        }
        cursor.close();
        return lesProfils;
    }

    /**
     * Supprime un profil dans la base de données locale SQLite.
     * @param profil
     */
    public void supprProfil(Profil profil) {
        bd = accesBD.getWritableDatabase();

        bd.delete("profil", "datemesure" + "=?" , new String[]{profil.getDateMesure().toString()});
    }
}
