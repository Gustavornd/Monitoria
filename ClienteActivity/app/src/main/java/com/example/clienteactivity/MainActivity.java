package com.example.clienteactivity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnListarClientes, btnAlterarCliente, btnExcluirCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ligando os botões do layout com as variáveis
        btnListarClientes = findViewById(R.id.btnListarClientes);
        btnAlterarCliente = findViewById(R.id.btnAlterarCliente);
        btnExcluirCliente = findViewById(R.id.btnExcluirCliente);

        // Configurando o evento de clique para redirecionar para a atividade de Listar Clientes
        btnListarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListarClientesActivity.class);
                startActivity(intent);
            }
        });

        // Configurando o evento de clique para redirecionar para a atividade de Alterar Cliente
        btnAlterarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlterarClienteActivity.class);
                startActivity(intent);
            }
        });

        // Configurando o evento de clique para redirecionar para a atividade de Excluir Cliente
        btnExcluirCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExcluirClienteActivity.class);
                startActivity(intent);
            }
        });
    }


}