package com.example.earth.serpelsolar;

import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Earth on 23/10/2016.
 */
public class Busca extends AppCompatActivity {
    List<String> opcoes;
    ArrayAdapter<String> adaptador;
    ListView listV;

  ListAdapter adapter;
    private BD bd;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busca);
        listV = (ListView) findViewById(android.R.id.list);
        bd = new BD(this);
    }

    public void setBd(BD banco){
        this.bd = banco;
    }
    public BD getBd(){
        return bd;
    }

    public void searchView(View view) {
        EditText sv = (EditText) findViewById(R.id.searchView);
        if(sv == null){
            Toast t = Toast.makeText(this,"Por favor! Digite o nome do cliente",Toast.LENGTH_SHORT);
            t.show();
        }else{
        List<Servicos> list = bd.buscaPorQuery(sv.getText().toString());
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
                        "\n\n" + "Tipo do Serviço: " + list.get(i).getTipo()+
                        "\n\n" + "Descrição do Serviço:\n\n" + list.get(i).getDesc() + "\n");
                i++;

            }
            adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcoes);
            listV.setAdapter(adaptador);

            }

        }
    }

    public void listaTodos(View view){
        List<Servicos> result = bd.buscaTodos();
        if(result.isEmpty() || result == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Mensagem");
            builder.setMessage("Busca não obteve resultados!\nTente novamente");
            builder.create().show();
        }
        int i = 0;
        opcoes = new ArrayList<String>();
        for (Servicos s : result) {
            opcoes.add("Cliente:\n\n " + result.get(i).getCliente() +
                    "\n\n" + "Preço do Serviço: \n\nR$" + result.get(i).getPreco() +
                    "\n\n" + "Descrição do Serviço:\n\n" + result.get(i).getDesc() + "\n");
            i++;

        }
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcoes);
        listV.setAdapter(adaptador);
    }



}
