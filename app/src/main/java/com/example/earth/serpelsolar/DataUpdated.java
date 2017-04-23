package com.example.earth.serpelsolar;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.util.List;

/**
 * Created by Earth on 20/11/2016.
 */
public class DataUpdated extends AppCompatActivity{

    public static EditText clienteupdate = null;
    public static EditText precoupdate = null;
    public static EditText descricaoupdate = null;
    public static List<Servicos> list;
    public static int position;
    public static String previousCliente;
    /* Definição dos switches */
    Switch tb;
    Switch tb2;
    Switch tb3;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.update_data);
        list = ListaUpdate.list;
        position = ListaUpdate.finalPosition;
        /* Definindo as variáveis para a atualização */

        clienteupdate = (EditText) findViewById(R.id.updateCliente);
        precoupdate = (EditText) findViewById(R.id.updatePreco);
        descricaoupdate = (EditText) findViewById(R.id.updateDescricao);

        tb = (Switch)findViewById(R.id.sw1);
        tb2 = (Switch)findViewById(R.id.sw2);
        tb3 = (Switch)findViewById(R.id.sw3);
        iniciadorDeCampos();




    }

    public void iniciadorDeCampos(){

        /* Setando os valoes dos updates como sendo os valores da lista realizada na busca, capturando a posição do elemento
        quando o usuário toca. position está definida no inicio da classe, bem como a list.
         */

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mensagem");
        builder.setMessage("Cliente "+list.get(position).getCliente()+" Selecionado");
        builder.create().show();



        previousCliente = list.get(position).getCliente();
        clienteupdate.setText(previousCliente);
        precoupdate.setText(list.get(position).getPreco());
        descricaoupdate.setText(list.get(position).getDesc());


        /* Definindo todos os campos para serem não editáveis,ou seja indisponíveis, para que assim o usuário possa ativar qual
        campo ele deseja ativar.
         */

        clienteupdate.setEnabled(false);
        precoupdate.setEnabled(false);
        descricaoupdate.setEnabled(false);


        /* Definindo a ação dos Switches, em Switch do cliente,preco e descrição. Passa-se como parâmentro uma instancia da classe CompoundButton.OnCheckedChangeListener()
        assim determinando qual o tipo de ação deverá ser executada.
        Na função onCheckedChanged, que deve ser implementada assim que for instanciada a CompoundButton.OnCheckedChangeListener().
        Dentro, será verificada se o switch estiver ligado, ele disponibiliza o campo para edição.
        Se o campo estiver ligado, e ele tocar novamente o campo passará a ficar desabilitado.
        */
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    clienteupdate.setEnabled(true);
                }else {
                    EditText clienteupdate = (EditText) findViewById(R.id.updateCliente);
                    clienteupdate.setEnabled(false);
                }
            }
        });

        tb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    precoupdate.setEnabled(true);
                }else {
                    EditText clienteupdate = (EditText) findViewById(R.id.updatePreco);
                    precoupdate.setEnabled(false);
                }
            }
        });

        tb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    descricaoupdate.setEnabled(true);
                }else {
                    EditText clienteupdate = (EditText) findViewById(R.id.updateDescricao);
                    descricaoupdate.setEnabled(false);
                }
            }
        });

    }

    public void salvarUpdate(View view){
        BD bd = new BD(this);
        EditText newcliente = (EditText)findViewById(R.id.updateCliente);
        EditText newpreco = (EditText)findViewById(R.id.updatePreco);
        EditText newdesc = (EditText)findViewById(R.id.updateDescricao);

        bd.alterar(previousCliente,newcliente.getText().toString(),newpreco.getText().toString(),newdesc.getText().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mensagem");
        builder.setMessage("Informações Atualizadas com sucesso");
        builder.create().show();

        clienteupdate = null;
        precoupdate = null;
        descricaoupdate = null;
    }

}
