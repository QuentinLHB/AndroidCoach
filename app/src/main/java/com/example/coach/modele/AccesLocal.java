package com.example.coach.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {
    private String nomBase = "bdCoach.sqlite";
    private int versionBase = 1;
    MySQLiteOpenHelper accesBD;
    SQLiteDatabase bd;

    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);

    }

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
}
