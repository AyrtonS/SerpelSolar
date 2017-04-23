package com.example.earth.serpelsolar;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Earth on 27/11/2016.
 */
public class DeleteData extends AppCompatActivity{


    private ListView listV;
    private ArrayAdapter<String> adapter;
    List<String> opcoes;
    private BD bd;
    AlertDialog.Builder builder;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_data);
        listV = (ListView) findViewById(R.id.deleteList);
        bd = new BD(this);
    }

    public void delete(View view){
        EditText key = (EditText) findViewById(R.id.deleteSearch);
        final List<Servicos> lista = bd.buscaPorQuery(key.getText().toString());
        if (lista.isEmpty()) {
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Mensagem");
            builder.setMessage("Busca não obteve resultados!\nTente novamente");
            builder.create().show();
        }
        opcoes = new ArrayList<String>();
        int i = 0;

        for (Servicos s : lista) {
            opcoes.add("Cliente:\n\n " + lista.get(i).getCliente() +
                    "\n\n" + "Preço do Serviço: \n\nR$" + lista.get(i).getPreco() +
                    "\n\n" + "Descrição do Serviço:\n\n" + lista.get(i).getDesc() + "\n");
            i++;

        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcoes);
        listV.setAdapter(adapter);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                boolean result = bd.delete(lista.get(position).getId());
                if(result) {
                    instantiateAlert();
                    builder.setTitle("Mensagem");
                    builder.setMessage("Exclusão de Dados do Cliente Completa");
                    builder.create().show();

                }
                else{
                    instantiateAlert();
                    builder.setTitle("Mensagem");
                    builder.setMessage("Não foi possivel deletar as informações do cliente");
                    builder.create().show();
                }
            }
        });



    }
    public void instantiateAlert(){
        this.builder = new AlertDialog.Builder(this);
    }
}
