package com.example.notepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity implements View.OnClickListener{
    Button btnSalvar, btnVoltar;
    EditText txtNomeCad, txtEmailCad, txtSenhaCad, txtConfSenhaCad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);

        txtNomeCad = (EditText) findViewById(R.id.txtNomeCad);
        txtEmailCad = (EditText) findViewById(R.id.txtEmailCad);
        txtSenhaCad = (EditText) findViewById(R.id.txtSenhaCad);
        txtConfSenhaCad = (EditText) findViewById(R.id.txtConfSenhaCad);
    }

    @Override
    public void onClick(View v) {
        String NomeCad = txtNomeCad.getText().toString();
        String EmailCad = txtEmailCad.getText().toString();
        String SenhaCad = txtSenhaCad.getText().toString();
        String ConfSenhaCad = txtConfSenhaCad.getText().toString();


        BancoController bd = new BancoController(getBaseContext());
        String resultado;

        if (SenhaCad.equals(ConfSenhaCad)) {
            resultado = bd.insereDadosUsuario(NomeCad, EmailCad, SenhaCad);

            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            limpar();
        }else{
            String msg = "As senhas digitadas não são iguais, digite novamente!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }
    }
    public void limpar()
    {
        txtNomeCad.setText("");
        txtEmailCad.setText("");
        txtSenhaCad.setText("");
        txtConfSenhaCad.setText("");
        txtNomeCad.requestFocus();
    }
}
