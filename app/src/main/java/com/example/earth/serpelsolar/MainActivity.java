package com.example.earth.serpelsolar;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.earth.serpelsolar.R.layout.activity_main;
import static com.example.earth.serpelsolar.R.layout.novo;

public class MainActivity extends AppCompatActivity {

    List<String> opcoes;
    ArrayAdapter<String> adaptador;
    ListView list;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        setContentView(activity_main);


        list = (ListView) findViewById(R.id.listView);

        opcoes = new ArrayList<String>();
        opcoes.add("NOVO SERVIÇO ");
        opcoes.add("BUSCAR SERVIÇOS");
        opcoes.add("EDITAR INFORMAÇÕES ");
        opcoes.add("EXCLUIR INFORMAÇÕES ");
        opcoes.add("ENVIAR EMAIL PARA CLIENTE");
        opcoes.add("SAIR ");

        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opcoes);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                switch (position){
                    case 0:
                        novo();
                        break;
                    case 1:
                        buscar();
                        break;
                    case 2:
                        update();
                        break;
                    case 3:
                        delete();
                        break;
                    case 4:
                        sendmail();
                        break;
                    case 5:
                        try {
                            sair();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                        break;
            }
            }

        });
        /*WebView web = (WebView) findViewById(R.id.webview);
        web.loadUrl("https://login.yahoo.com");*/
    }

    public void novo(){
        Intent intent = new Intent(MainActivity.this,Novo.class);
        startActivity(intent);
    }
    public void buscar(){
        Intent intent = new Intent(MainActivity.this,TipoBuscas.class);
        startActivity(intent);

    }


    public void sair() throws Throwable {
       /* BD bd = new BD(this);
        bd.destroy();*/
        finish();
    }

    private AlertDialog alerta;
    public void salvar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mensagem");
        builder.setMessage("Informação salva com sucesso");
        alerta = builder.create();
        alerta.show();
    }
    public void sendmail(){
        Intent intent = new Intent(this,SendMail.class);
        startActivity(intent);
    }
    public void update(){
        Intent intent = new Intent(this,ListaUpdate.class);
        startActivity(intent);
    }
    public void delete(){
        Intent intent = new Intent(this,DeleteData.class);
        startActivity(intent);
    }


}
