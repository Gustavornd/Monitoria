package com.example.mercadinho;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class ListaActivity extends AppCompatActivity {
    private ListView lvListar;
    SQLiteDatabase banco;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets)-> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            lvListar = findViewById(R.id.lvListar);
            banco = this.openOrCreateDatabase("banco", Context.MODE_PRIVATE, null);
            montarLista();
            return insets;
        });
    }
    @SuppressLint("deprecation")
    private void montarLista(){
        Cursor registros = banco.query("notas", null, null, null, null, null, null, null);
        String atributos[] = new String[] {"descricao", "unidade", "preco"};
        int atributosTela[] = new int[] {android.R.id.text1, android.R.id.text2};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                android.R.layout.two_line_list_item, registros, atributos, atributosTela);
        lvListar.setAdapter(adaptador);
    }
}
