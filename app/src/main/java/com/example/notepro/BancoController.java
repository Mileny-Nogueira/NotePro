package com.example.notepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private Bd banco;

    public BancoController(Context context) {
        banco = new Bd(context);
    }



    public String insereDadosUsuario(String nome, String email, String senha) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);
        valores.put("senha", senha);

        resultado = db.insert("usuarios", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro os dados, tente novamente!";
        else
            return "Dados do Usuário cadastrado com sucesso!";
    }

    public String insereDado(String nome, String email) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);

        resultado = db.insert("contatos", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }


    public Cursor carregaDadosLogin(String Login, String SenhaLogin) {
        Cursor cursor;
        String[] campos = { "codigo", "nome", "email", "senha" };
        String where = "email = '" + Login + "' and senha = '" + SenhaLogin + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}

