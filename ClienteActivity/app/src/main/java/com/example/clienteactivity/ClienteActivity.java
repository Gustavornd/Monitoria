package com.example.clienteactivity;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ClienteActivity extends AppCompatActivity {
    private ClienteDbHelper dbHelper;

    private EditText etNome,
            etLogradouro,
            etNumero,
            etBairro,
            etCidade,
            etEstado,
            etCep,
            etTelefone,
            etCpf,
            etDiaVencimento,
            etComplemento,
            etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        dbHelper = new ClienteDbHelper(this);

        etNome = findViewById(R.id.etNome);
        etLogradouro = findViewById(R.id.etLogradouro);
        etNumero = findViewById(R.id.etNumero);
        etBairro = findViewById(R.id.etBairro);
        etCidade = findViewById(R.id.etCidade);
        etEstado = findViewById(R.id.etEstado);
        etCep = findViewById(R.id.etCep);
        etTelefone = findViewById(R.id.etTelefone);
        etCpf = findViewById(R.id.etCpf);
        etDiaVencimento = findViewById(R.id.etDiaVencimento);
        etComplemento = findViewById(R.id.etComplemento);
        etEmail = findViewById(R.id.etEmail);

        findViewById(R.id.btnSalvar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCliente();
            }
        });
    }

    private void salvarCliente() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", etNome.getText().toString());
        values.put("logradouro", etLogradouro.getText().toString());
        values.put("numero", etNumero.getText().toString());
        values.put("bairro", etBairro.getText().toString());
        values.put("cidade", etCidade.getText().toString());
        values.put("estado", etEstado.getText().toString());
        values.put("cep", etCep.getText().toString());
        values.put("telefone", etTelefone.getText().toString());
        values.put("cpf", etCpf.getText().toString());
        values.put("diaVencimento", etDiaVencimento.getText().toString());
        values.put("complemento", etComplemento.getText().toString());
        values.put("email", etEmail.getText().toString());

        long newRowId = db.insert("cliente", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao salvar cliente", Toast.LENGTH_SHORT).show();
        }
    }


}
