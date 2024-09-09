package com.andresc.atividade03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void cliente (View v) {
        Button newCliente = findViewById(R.id.button);
        newCliente.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intencao = new Intent(MainActivity.this, CadastrarCliente.class);
                startActivity(intencao);
            }
        });

    }
    public void produto (View v) {
        Intent intencao = new Intent(MainActivity.this, CadastrarCliente.class);
        startActivity(intencao);
    }
}