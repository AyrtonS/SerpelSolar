package com.example.earth.serpelsolar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Earth on 23/10/2016.
 */
public class BDcore extends SQLiteOpenHelper{
    public static final String NOME_BD = "serpel";
    public static final int VERSAO_BD = 1;


    public BDcore(Context cont){
        super(cont,NOME_BD,null,VERSAO_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table servicos (_id integer primary key autoincrement," +
                    "cliente text not null," +
                    "preco text not null," +
                    "tipo text not null," +
                    "desc text not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table servicos");
        onCreate(db);
    }
}
