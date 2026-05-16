package com.example.sistemagestaoprodutos;

import android.app.AlertDialog;
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

public class CadastrarActivity extends AppCompatActivity {

    EditText edNomeProduto, edValorProduto, edQuantidadeProduto;

    Button btnSalvarProduto, btnVoltar;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar);

        databaseHelper = new DatabaseHelper(this);

        edNomeProduto = findViewById(R.id.edNomeProduto);
        edValorProduto = findViewById(R.id.edValorProduto);
        edQuantidadeProduto = findViewById(R.id.edQuantidadeProduto);

        btnSalvarProduto = findViewById(R.id.btnSalvarProduto);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = edNomeProduto.getText().toString();
                String valor = edValorProduto.getText().toString();
                String quantidade = edQuantidadeProduto.getText().toString();

                if (nome.isEmpty() || valor.isEmpty() || quantidade.isEmpty()) {

                    MostraMensagem("Erro: Todos os campos são obrigatórios");

                } else if (valor.equals("0") || quantidade.equals("0")){

                    MostraMensagem("Erro: Não é possivel salvar dados zerados");

                } else {

                    Produtos produtos = new Produtos(
                            nome,
                            Double.parseDouble(valor),
                            Double.parseDouble(quantidade)
                    );

                    databaseHelper.adicionarProduto(produtos);

                    edNomeProduto.setText("");
                    edValorProduto.setText("");
                    edQuantidadeProduto.setText("");

                    MostraMensagem("Produto cadastrado!");
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CadastrarActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

    }

    public void MostraMensagem(String str)  {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastrarActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}