package com.example.clienteactivity;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AlterarClienteActivity extends AppCompatActivity {

    private EditText etIdCliente, etNome, etCpf;
    private Button btnAlterar;
    private SQLiteDatabase banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_cliente);

        etIdCliente = findViewById(R.id.etIdCliente);
        etNome = findViewById(R.id.etNome);
        etCpf = findViewById(R.id.etCpf);
        btnAlterar = findViewById(R.id.btnAlterar);

        // Abrindo ou criando o banco de dados
        banco = this.openOrCreateDatabase("appVendas", Context.MODE_PRIVATE, null);

        // Ao clicar no botão "Alterar"
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarCliente();
            }
        });
    }

    private void alterarCliente() {
        int idCliente = Integer.parseInt(etIdCliente.getText().toString());
        String nome = etNome.getText().toString();
        String cpf = etCpf.getText().toString();

        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("cpf", cpf);

        int linhasAfetadas = banco.update("cliente", valores, "idCliente = ?", new String[]{String.valueOf(idCliente)});
        if (linhasAfetadas > 0) {
            Toast.makeText(this, "Cliente alterado com sucesso!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Erro ao alterar cliente.", Toast.LENGTH_LONG).show();
        }
    }

    public void excluirCliente(View v) {
        int idCliente = Integer.parseInt(etIdCliente.getText().toString());

        int linhasExcluidas = banco.delete("cliente", "idCliente = ?", new String[]{String.valueOf(idCliente)});
        if (linhasExcluidas > 0) {
            Toast.makeText(this, "Cliente excluído com sucesso!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Erro ao excluir cliente.", Toast.LENGTH_LONG).show();
        }
    }

}
