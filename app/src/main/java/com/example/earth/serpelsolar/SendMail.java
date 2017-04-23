package com.example.earth.serpelsolar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import static com.example.earth.serpelsolar.R.layout.activity_main;

/**
 * Created by Earth on 16/11/2016.
 */
public class SendMail extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_mail);
    }
    public void waitSIGN(View view){
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this,"Disponível nas próximas versões",duracao);
        toast.show();
    }
}
