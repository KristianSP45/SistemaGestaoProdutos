package com.example.sistemagestaoprodutos;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class LoginActivity extends AppCompatActivity {

    EditText edEmailCadastro, edSenhaCadastro;

    Button btnSalvarCadastro, btnVoltarLogin;

    DatabaseHelper databaseHelper;

    TextView txtDisplayInfoCad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        edEmailCadastro = findViewById(R.id.edEmailCadastro);
        edSenhaCadastro = findViewById(R.id.edSenhaCadastro);
        //txtDisplayInfoCad = findViewById(R.id.txtDisplayInfoCad);

        btnSalvarCadastro = findViewById(R.id.btnSalvarCadastro);
        btnVoltarLogin = findViewById(R.id.btnVoltarLogin);

        btnVoltarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnSalvarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario(
                        edEmailCadastro.getText().toString(),
                        edSenhaCadastro.getText().toString()
                );

                if (usuario.getEmail().isEmpty() || usuario.getSenha().isEmpty()){
                    MostraMensagem("Erro: Todos os campos são obrigatorios");
                } else {
                        if (databaseHelper.verificarEmailExiste(usuario.getEmail())){
                            MostraMensagem("Erro: Esse email ja está em uso");
                        } else {
                            databaseHelper.adicionarUsuario(usuario);
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.putExtra("cadastro", "sucesso");
                            startActivity(i);
                        }
                }

            }
        });

    }

    public void MostraMensagem(String str)  {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(LoginActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}