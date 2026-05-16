package com.example.sistemagestaoprodutos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edEmail, edSenha;
    Button btnLogin, btnCadastrar;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        edEmail = findViewById(R.id.edEmail);
        edSenha = findViewById(R.id.edSenha);

        btnLogin = findViewById(R.id.btnLogin);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        String cadastro =
                getIntent().getStringExtra("cadastro");

        if (cadastro != null){
            MostraMensagem("Cadastro feito com sucesso!");
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario(
                        edEmail.getText().toString(),
                        edSenha.getText().toString()
                );

                if (usuario.getEmail().isEmpty() || usuario.getSenha().isEmpty()){
                    MostraMensagem("Erro: Todos os campos são obrigatorios");
                } else {
                    if (databaseHelper.verificarEmailSenha(usuario.getEmail(), usuario.getSenha())){
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(i);
                    } else {
                        MostraMensagem("Erro: Email ou senha invalido");
                    }
                }
            }
        });

    }

    public void MostraMensagem(String str)  {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}