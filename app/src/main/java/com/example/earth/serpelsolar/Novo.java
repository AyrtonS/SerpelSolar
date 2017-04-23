package com.example.earth.serpelsolar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Earth on 22/10/2016.
 */
public class Novo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo);
    }
    private AlertDialog alerta;

    private Context cont;
    public BD banco;
    public void salvarBD(View view) {

        EditText cliente = (EditText) findViewById(R.id.editText4);
        //EditText tipo = (EditText)findViewById(R.id.editText3);
        EditText preco =(EditText) findViewById(R.id.editText5);
        EditText desc = (EditText) findViewById(R.id.editText3);

        RadioGroup radioG = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedID = radioG.getCheckedRadioButtonId();
        RadioButton selected = (RadioButton) radioG.findViewById(selectedID);
        String value = selected.getText().toString();


        Toast toast1 = Toast.makeText(this,value,Toast.LENGTH_SHORT);
        toast1.show();


        banco = new BD(this);
        banco.inserir(cliente.getText().toString(),preco.getText().toString(),value,desc.getText().toString());
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mensagem");
        builder.setMessage("Informação de serviço salva com sucesso!");
        alerta = builder.create();
        alerta.show();
*/
        Toast toast = Toast.makeText(this,"Informação Salva com Sucesso!",Toast.LENGTH_SHORT);
        toast.show();

        Intent intent =  new Intent(this,MainActivity.class);
        startActivity(intent);

    }


}
