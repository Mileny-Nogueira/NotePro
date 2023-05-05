package com.example.notepro;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; //alt + enter para fazer um import
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btEntrar, btCadastro;
    EditText editTextPerson, editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btEntrar  = (Button) findViewById(R.id.btEntrar);
        btCadastro = (Button) findViewById(R.id.btCadastro); //R é um arquivo que é criado automaticamente que contem um catalogo de activity
        editTextPerson = (EditText) findViewById(R.id.editTextPerson);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        btEntrar.setOnClickListener(this);
        btCadastro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //este bloco de código será executado quando houver um click em um dos botões (acessar ou novouser)
        if (v.getId()==R.id.btCadastro){
            Intent intencao = new Intent(this, Cadastro.class);
            startActivity(intencao);
        }
        if (v.getId() == R.id.btEntrar) {
            consultaUsuarioLogin();
        }
    }

    public void consultaUsuarioLogin()
    {
        String Login = editTextPerson.getText().toString();
        String SenhaLogin = editTextPassword.getText().toString();

        BancoController bd = new BancoController(getBaseContext());

        Cursor dados = bd.carregaDadosLogin(Login, SenhaLogin) ;

        if(dados.moveToFirst()){
            Intent tela = new Intent(this, Lista.class);
            startActivity(tela);
        }else{
            String msg= "Usuário e senha inválidos, tente novamente!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            limpar();
        }
    }
    public void limpar(){
        editTextPerson.setText("");
        editTextPassword.setText("");
        editTextPerson.requestFocus();
    }
}
