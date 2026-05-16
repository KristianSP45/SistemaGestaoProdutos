package com.example.sistemagestaoprodutos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    Button btnCadastrarProduto, btnConsultarProduto, btnEditarProduto, btnExcluirProduto, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        btnCadastrarProduto = findViewById(R.id.btnCadastrarProduto);
        btnConsultarProduto = findViewById(R.id.btnConsultarProduto);
        btnEditarProduto = findViewById(R.id.btnEditarProduto);
        btnExcluirProduto = findViewById(R.id.btnExcluirProduto);
        btnSair = findViewById(R.id.btnSair);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnCadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, CadastrarActivity.class);
                startActivity(i);
            }
        });

        btnConsultarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ConsultarActivity.class);
                startActivity(i);
            }
        });

        btnEditarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, EditarActivity.class);
                startActivity(i);
            }
        });

        btnExcluirProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, DeletarActivity.class);
                startActivity(i);
            }
        });

    }
}