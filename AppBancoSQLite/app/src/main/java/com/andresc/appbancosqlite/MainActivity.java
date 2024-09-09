package com.andresc.appbancosqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private EditText etCodigo,etDisciplina,etNota;
    private SQLiteDatabase banco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                    systemBars.bottom);
// Nosso codigo fonte JAVA - Espelhando os objetos do LayOut nos objetos Java
            etCodigo=findViewById(R.id.etCodigo);
            etDisciplina=findViewById(R.id.etDisciplina);
            etNota=findViewById(R.id.etnota);
// Criando a instancia do banco
            banco = this.openOrCreateDatabase("banco", Context.MODE_PRIVATE,null);
            banco.execSQL("CREATE TABLE IF NOT EXISTS notas (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "disciplina TEXT NOT NULL, nota DECIMAL NOT NULL)");
            return insets;
        });
    }
    // Nossos Metodos para respostar aos Clicks nos botões do Layout
    public void inclusao(View v){
        ContentValues registro = new ContentValues();
        registro.put("disciplina",etDisciplina.getText().toString());
        registro.put("nota",Double.parseDouble(etNota.getText().toString()));
        banco.insert("notas",null,registro);
        Toast.makeText(this, "Registro Incluido com Sucesso!", Toast.LENGTH_LONG).show();
    }
    public void alterar(View v){
        int key = Integer.parseInt(etCodigo.getText().toString());
        ContentValues registro = new ContentValues();
        registro.put("disciplina",etDisciplina.getText().toString());
        registro.put("nota",Double.parseDouble(etNota.getText().toString()));
        banco.update("notas",registro,"_id = "+ key, null);
        Toast.makeText(this, "Registro Alterado com Sucesso!", Toast.LENGTH_LONG).show();
    }
    public void excluir(View v){
        int key = Integer.parseInt(etCodigo.getText().toString());
        banco.delete("notas"," _id = "+ key,null);
        Toast.makeText(this, "Registro Excluido com Sucesso!", Toast.LENGTH_LONG).show();
    }
    public void pesquisar(View v){
        final EditText etpesquisa = new EditText(getApplicationContext());
        etpesquisa.setTextColor(Color.BLACK);
        AlertDialog.Builder telaPesquisa = new AlertDialog.Builder(this);
        telaPesquisa.setTitle("Pesquisar");
        telaPesquisa.setMessage("Código a ser pesquisado: ");
        telaPesquisa.setView(etpesquisa);
        telaPesquisa.setNegativeButton("Cancelar",null);
        telaPesquisa.setPositiveButton("Pesquisar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                executarPesquisa(Integer.parseInt(etpesquisa.getText().toString()));
            }
        });
        telaPesquisa.show();
    }
    @SuppressLint("Range")
    protected void executarPesquisa(int id) {
        Cursor registros = banco.query("notas", null, " _id = " + id,
                null, null, null, null);
        if (registros.moveToNext()) {
//String nomeDisciplina =
            String.valueOf(registros.getColumnIndex("disciplina"));
            String nomeDisciplina =
                    registros.getString(registros.getColumnIndex("disciplina"));
            double nota = registros.getDouble(registros.getColumnIndex("nota"));
            etCodigo.setText(String.valueOf(id));
            etDisciplina.setText(nomeDisciplina);
            etNota.setText(String.valueOf(nota));
        }
    }
    public void listar(View v){
        Intent intencao = new Intent(MainActivity.this,ListaActivity.class);
        startActivity(intencao);
    }
}