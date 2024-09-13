package com.example.clienteactivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ExcluirClienteActivity extends AppCompatActivity {

    private ListView listViewClientes;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listaClientes; // Lista para exibir os nomes dos clientes
    private ArrayList<Integer> idsClientes; // Lista de IDs dos clientes
    private SQLiteDatabase banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.excluir_cliente);

        listViewClientes = findViewById(R.id.listViewClientes);

        // Criando a instância do banco
        banco = openOrCreateDatabase("banco", MODE_PRIVATE, null);

        // Carregar clientes do banco de dados
        carregarClientes();

        // Configurar o ArrayAdapter para exibir os nomes no ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);
        listViewClientes.setAdapter(adapter);

        // Adicionar evento de clique nos itens do ListView
        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confirmarExclusao(position);
            }
        });
    }

    // Função para carregar a lista de clientes do banco de dados
    private void carregarClientes() {
        Cursor cursor = banco.query("cliente", null, null, null, null, null, null);
        listaClientes = new ArrayList<>();
        idsClientes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                int id = cursor.getInt(cursor.getColumnIndex("idCliente"));
                listaClientes.add(nome);
                idsClientes.add(id);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    // Função para confirmar a exclusão de um cliente
    private void confirmarExclusao(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Excluir Cliente")
                .setMessage("Tem certeza que deseja excluir o cliente " + listaClientes.get(position) + "?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluirCliente(position);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    // Função para excluir o cliente
    private void excluirCliente(int position) {
        int id = idsClientes.get(position);
        banco.delete("cliente", "idCliente = ?", new String[]{String.valueOf(id)});

        // Atualizar a lista e o ListView
        listaClientes.remove(position);
        idsClientes.remove(position);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "Cliente excluído com sucesso.", Toast.LENGTH_SHORT).show();
    }
}

