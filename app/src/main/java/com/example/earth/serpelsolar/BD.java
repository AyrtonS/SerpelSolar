package com.example.earth.serpelsolar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Earth on 23/10/2016.
 */
public class BD extends ActionBarActivity {

    private SQLiteDatabase db;
    BDcore auxdb;
    ContentValues values = new ContentValues();
    public BD(Context ctx){
        auxdb = new BDcore(ctx);
        db = auxdb.getWritableDatabase();
    }

    public void inserir(String cliente, String preco, String tipo,String DESC){
        try{
            values.put("cliente",cliente);
            values.put("tipo", tipo);
            values.put("preco",preco );
            values.put("desc",DESC);
            db.insert("servicos",null,values);
        }catch(SQLiteConstraintException e){
            Log.getStackTraceString(e.getCause());
        }

    }

    public List<Servicos> buscaPorQuery(String query){
        if(query.isEmpty() || query.equals(" ")) return null;

        List<Servicos> lista = new ArrayList<Servicos>();
        Cursor cursor = db.rawQuery("select _id,cliente,preco,tipo,desc from servicos where cliente like '%"+query+"%'",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Servicos s = new Servicos();
            s.setId(cursor.getString(cursor.getColumnIndex("_id")));
            s.setCliente(cursor.getString(cursor.getColumnIndex("cliente")));
            s.setPreco(cursor.getString(cursor.getColumnIndex("preco")));
            s.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
            s.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
            lista.add(s);
            cursor.moveToNext();
        }

        return lista;
    };

    public List<Servicos> buscaTodos(){
        List<Servicos> lista = new ArrayList<Servicos>();
        Cursor cursor = db.rawQuery("select _id,cliente,preco,tipo,desc",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Servicos s = new Servicos();
            s.setId(cursor.getString(cursor.getColumnIndex("_id")));
            s.setCliente(cursor.getString(cursor.getColumnIndex("cliente")));
            s.setPreco(cursor.getString(cursor.getColumnIndex("preco")));
            s.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
            s.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
            cursor.moveToNext();
        }
        return lista;
    }

    public void alterar(String previousCliente,String cliente, String preco, String desc){
        ContentValues values = new ContentValues();
        values.put("cliente",cliente);
        values.put("preco",preco);
        values.put("desc",desc);
        String whereClause = "cliente='"+previousCliente+"'";
        db.update("servicos",values,whereClause,null);
    }

    public boolean delete(String id){
        ContentValues values = new ContentValues();
        if(db.delete("servicos","_id='"+id+"'",null)==0){
            return false;
        }
        return true;
    }


    public void destroy(){
        auxdb.onUpgrade(db,1,2);
    }
}
