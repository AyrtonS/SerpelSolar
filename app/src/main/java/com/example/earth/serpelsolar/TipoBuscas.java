package com.example.earth.serpelsolar;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Earth on 14/11/2016.
 */
public class TipoBuscas extends AppCompatActivity{
    private ListView listV;
    private ArrayAdapter<String> adapter;
    private BD bd;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipo_buscas);
        listV = (ListView) findViewById(R.id.tipos);
        bd = new BD(this);
        List<String> opcoes = new ArrayList<String>();
        opcoes.add("BUSCAR POR CLIENTE ");
        opcoes.add("VOLTAR ");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcoes);
        listV.setAdapter(adapter);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                switch (position){
                    case 0:
                        BuscaCliente();
                        break;
                    case 1:
                        voltar();
                        break;
                }
            }
        });
    }

    public void BuscaCliente(){
        Intent intent = new Intent(this,Busca.class);
        startActivity(intent);
    }
    public void BuscaProjeto(){
        Intent intent = new Intent(this,Busca.class);
        startActivity(intent);
    }
    public void BuscaTudo(){
        bd.buscaTodos();
    }
    public void voltar(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
