package com.example.mercadinho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.graphics.Insets;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etDescricao, etUnidade, etPreco, etCodigo;
    private SQLiteDatabase banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),(v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            etDescricao = findViewById(R.id.etDescricao);
            etUnidade = findViewById(R.id.etUnidade);
            etPreco = findViewById(R.id.etPreco);

            banco = this.openOrCreateDatabase("banco", Context.MODE_PRIVATE, null);
            banco.execSQL("CREATE TABLE IF NOT EXISTS Produto (" +
                    "idProduto INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descricao TEXT NOT NULL, " +
                    "unidade TEXT NOT NULL, " +
                    "preco REAL NOT NULL);");
            return insets;
        });

        public void inclusao(View v){
            ContentValues registo = new ContentValues();
        }






        // Espelhar os componentes do layout nos objetos Java
        etDescricao = findViewById(R.id.etDescricao);
        etUnidade = findViewById(R.id.etUnidade);
        etPreco = findViewById(R.id.etPreco);
        etCodigo = findViewById(R.id.etCodigo); // Campo para o ID do produto

        // Criação ou abertura do banco de dados
        banco = this.openOrCreateDatabase("mercadoDB", Context.MODE_PRIVATE, null);
        banco.execSQL("CREATE TABLE IF NOT EXISTS Produto (" +
                "idProduto INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "descricao TEXT NOT NULL, " +
                "unidade TEXT NOT NULL, " +
                "preco REAL NOT NULL);");
    }

    // Função para obter o ID do produto inserido no campo etCodigo
    private int getIdProduto() {
        // Verifica se o campo etCodigo está vazio antes de tentar converter para int
        String codigoStr = etCodigo.getText().toString();
        if (codigoStr.isEmpty()) {
            Toast.makeText(this, "Por favor, insira o ID do produto.", Toast.LENGTH_SHORT).show();
            return -1; // Retorna -1 se o campo estiver vazio
        }
        return Integer.parseInt(codigoStr);
    }

    public void salvarProduto(View v) {
        ContentValues registro = new ContentValues();
        registro.put("descricao", etDescricao.getText().toString());
        registro.put("unidade", etUnidade.getText().toString());
        registro.put("preco", Double.parseDouble(etPreco.getText().toString()));

        banco.insert("Produto", null, registro);
        Toast.makeText(this, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show();
        limparCampos();
    }

    public void alterarProduto(View v) {
        int idProduto = getIdProduto(); // Obtém o ID do produto
        if (idProduto == -1) return; // Verifica se o ID é válido

        ContentValues registro = new ContentValues();
        registro.put("descricao", etDescricao.getText().toString());
        registro.put("unidade", etUnidade.getText().toString());
        registro.put("preco", Double.parseDouble(etPreco.getText().toString()));

        int rowsAffected = banco.update("Produto", registro, "idProduto = ?", new String[]{String.valueOf(idProduto)});
        if (rowsAffected > 0) {
            Toast.makeText(this, "Produto alterado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show();
        }
    }

    public void excluirProduto(View v) {
        int idProduto = getIdProduto(); // Obtém o ID do produto
        if (idProduto == -1) return; // Verifica se o ID é válido

        int rowsDeleted = banco.delete("Produto", "idProduto = ?", new String[]{String.valueOf(idProduto)});
        if (rowsDeleted > 0) {
            Toast.makeText(this, "Produto excluído com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show();
        }
    }

    public void pesquisarProduto(View v) {
        int idProduto = getIdProduto(); // Obtém o ID do produto
        if (idProduto == -1) return; // Verifica se o ID é válido

        Cursor cursor = banco.query("Produto", null, "idProduto = ?", new String[]{String.valueOf(idProduto)},
                null, null, null);

        if (cursor.moveToFirst()) {
            etDescricao.setText(cursor.getString(cursor.getColumnIndexOrThrow("descricao")));
            etUnidade.setText(cursor.getString(cursor.getColumnIndexOrThrow("unidade")));
            etPreco.setText(cursor.getString(cursor.getColumnIndexOrThrow("preco")));
        } else {
            Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show();
        }
    }

    public void listarProdutos(View v) {
        Cursor cursor = banco.rawQuery("SELECT * FROM Produto", null);

        StringBuilder listagem = new StringBuilder();
        while (cursor.moveToNext()) {
            listagem.append("ID: ").append(cursor.getInt(cursor.getColumnIndexOrThrow("idProduto"))).append("\n");
            listagem.append("Descrição: ").append(cursor.getString(cursor.getColumnIndexOrThrow("descricao"))).append("\n");
            listagem.append("Unidade: ").append(cursor.getString(cursor.getColumnIndexOrThrow("unidade"))).append("\n");
            listagem.append("Preço: ").append(cursor.getDouble(cursor.getColumnIndexOrThrow("preco"))).append("\n\n");
        }

        Toast.makeText(this, listagem.toString(), Toast.LENGTH_LONG).show();
    }

    private void limparCampos() {
        etDescricao.setText("");
        etUnidade.setText("");
        etPreco.setText("");
        etCodigo.setText(""); // Limpa o campo de código (ID)
    }
}
