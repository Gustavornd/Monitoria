package com.andresc.atividade03;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
public class ClienteDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "clientes.db";
    private static final int DATABASE_VERSION = 1;

    public ClienteDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public  void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cliente ("+
                "idCliente INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nome TEXT NOT NULL,"+
                "logradouro TEXT NOT NULL,"+
                "numero TEXT NOT NULL,"+
                "bairro TEXT NOT NULL,"+
                "cidade TEXT NOT NULL,"+
                "estado TEXT NOT NULL,"+
                "cep TEXT NOT NULL,"+
                "telefone TEXT NOT NULL,"+
                "cpf TEXT NOT NULL,"+
                "diaVencimento TEXT NOT NULL,"+
                "complemento TEXT NOT NULL,"+
                "email TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS cliente");
        onCreate(db);
    }
}
