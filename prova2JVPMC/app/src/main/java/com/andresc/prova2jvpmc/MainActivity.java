package com.andresc.prova2jvpmc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);

    }

    public void ouvir(View v){
        boolean tudook=true;
        if (TextUtils.isEmpty(edit1.getText().toString())) {
            edit1.setError("Favor digitar o email");
            edit1.requestFocus();
            tudook=false;
            return;
        }
        if (TextUtils.isEmpty(edit2.getText().toString())) {
            edit2.setError("Favor digitar a senha");
            edit2.requestFocus();
            tudook=false;
            return;
        }
// se chegou aqui, está tudo OK - proxima tela
        if (tudook){
            if (edit1.getText().toString().equals("jvpmc@tecnet.com") &&
                    edit2.getText().toString().equals("provajvpmc")){

                Intent intencao = new Intent(MainActivity.this,cadastrar.class);
                startActivity(intencao);
            } else {
                Toast.makeText(MainActivity.this,"DADOS INCORRETOS",Toast.LENGTH_LONG).show();
            }
        }

    }

}
