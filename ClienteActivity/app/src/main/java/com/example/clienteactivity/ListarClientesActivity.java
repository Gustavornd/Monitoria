package com.example.clienteactivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class ListarClientesActivity extends AppCompatActivity {
    private ListView lvClientes;
    private SQLiteDatabase banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_clientes);

        lvClientes = findViewById(R.id.lvClientes);

        // Abrindo ou criando o banco de dados
        banco = this.openOrCreateDatabase("appVendas", Context.MODE_PRIVATE, null);

        // Criando a tabela clientes, se não existir
        banco.execSQL("CREATE TABLE IF NOT EXISTS cliente ("
                + "idCliente INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome TEXT NOT NULL, "
                + "logradouro TEXT NOT NULL, "
                + "numero TEXT NOT NULL, "
                + "bairro TEXT NOT NULL, "
                + "cidade TEXT NOT NULL, "
                + "estado TEXT NOT NULL, "
                + "cep TEXT NOT NULL, "
                + "telefone TEXT NOT NULL, "
                + "cpf TEXT NOT NULL, "
                + "diaVencimento TEXT NOT NULL, "
                + "complemento TEXT NOT NULL, "
                + "email TEXT NOT NULL);");

        // Carregando os dados da tabela
        listarClientes();
    }

    private void listarClientes() {
        Cursor registros = banco.query("cliente", null, null, null, null, null, null);
        String[] campos = new String[]{"nome", "cpf"};
        int[] componentes = new int[]{android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(
                getApplicationContext(),
                android.R.layout.two_line_list_item,
                registros,
                campos,
                componentes,
                0
        );

        lvClientes.setAdapter(adaptador);
    }


}
