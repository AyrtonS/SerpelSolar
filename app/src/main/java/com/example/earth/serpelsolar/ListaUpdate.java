package com.example.earth.serpelsolar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Earth on 20/11/2016.
 */
public class ListaUpdate extends AppCompatActivity {

    List<String> opcoes;
    ArrayAdapter<String> adaptador;
    ListView listV;

    ListAdapter adapter;
    private BD bd;
    SQLiteDatabase db;
    AlertDialog.Builder builder;

    public static List<Servicos> list = null;
    public static int finalPosition = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_update);
        listV = (ListView) findViewById(R.id.listUpdate);
        bd = new BD(this);
    }

    public void setBd(BD banco){
        this.bd = banco;
    }
    public BD getBd(){
        return bd;
    }

    public void UpdateView(View view) {
        EditText sv = (EditText) findViewById(R.id.pesquisaUpdate);
        list = bd.buscaPorQuery(sv.getText().toString());
        if (list == null || list.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Mensagem");
            builder.setMessage("Busca não obteve resultados!\nTente novamente");
            builder.create().show();
        }else{
            opcoes = new ArrayList<String>();
            int i = 0;

            for (Servicos s : list) {
                opcoes.add("Cliente: " + list.get(i).getCliente() +
                        "\n\n" + "Preço do Serviço: R$" + list.get(i).getPreco() +",00"+
                        "\n\n" + "Descrição do Serviço:\n\n" + list.get(i).getDesc() + "\n");
                i++;

            }

            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes);
            listV.setAdapter(adaptador);
            listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*instantiateAlert();
                builder.setTitle("Mensagem");
                builder.setMessage(""+position);
                builder.create().show();
*/
                    finalPosition = position;
                    startUpdate();
                }
            });

        }

    }
    public void instantiateAlert(){
            this.builder = new AlertDialog.Builder(this);
    }
    public void startUpdate() {

        Intent intent = new Intent(this, DataUpdated.class);
        startActivity(intent);
    }


}


