package com.example.sistemagestaoprodutos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditarActivity extends AppCompatActivity {

    EditText edIdProduto, edNomeProduto, edValorProduto, edQuantidadeProduto;

    Button btnVoltar, btnAlterar;

    DatabaseHelper databaseHelper;

    ImageView imgPrimeiro, imgAnterior, imgProximo, imgUltimo;

    Cursor c;

    int numreg;

    DialogInterface.OnClickListener diAlterarInformaçoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar);

        databaseHelper = new DatabaseHelper(this);

        edIdProduto = findViewById(R.id.edIdProduto);
        edNomeProduto = findViewById(R.id.edNomeProduto);
        edValorProduto = findViewById(R.id.edValorProduto);
        edQuantidadeProduto = findViewById(R.id.edQuantidadeProduto);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnAlterar = findViewById(R.id.btnAlterar);

        imgPrimeiro = (ImageView) findViewById(R.id.imgPrimeiro);
        imgAnterior = (ImageView) findViewById(R.id.imgAnterior);
        imgProximo = (ImageView) findViewById(R.id.imgProximo);
        imgUltimo = (ImageView) findViewById(R.id.imgUltimo);

        c = databaseHelper.consultarProdutos();

        if(c != null && c.moveToFirst()){

            numreg = c.getInt(0);

            edIdProduto.setText(c.getString(0));
            edNomeProduto.setText(c.getString(1));
            edValorProduto.setText(c.getString(2));
            edQuantidadeProduto.setText(c.getString(3));

        } else {
            MostraMensagem("Nenhum registro encontrado");
        }

        imgPrimeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c.moveToFirst()){

                    numreg = c.getInt(0);

                    edIdProduto.setText(c.getString(0));
                    edNomeProduto.setText(c.getString(1));
                    edValorProduto.setText(c.getString(2));
                    edQuantidadeProduto.setText(c.getString(3));

                }
            }
        });

        imgAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c.moveToPrevious()){

                    numreg = c.getInt(0);

                    edIdProduto.setText(c.getString(0));
                    edNomeProduto.setText(c.getString(1));
                    edValorProduto.setText(c.getString(2));
                    edQuantidadeProduto.setText(c.getString(3));

                }
            }
        });

        imgProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c.moveToNext()){

                    numreg = c.getInt(0);

                    edIdProduto.setText(c.getString(0));
                    edNomeProduto.setText(c.getString(1));
                    edValorProduto.setText(c.getString(2));
                    edQuantidadeProduto.setText(c.getString(3));

                }
            }
        });

        imgUltimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c.moveToLast()){

                    numreg = c.getInt(0);

                    edIdProduto.setText(c.getString(0));
                    edNomeProduto.setText(c.getString(1));
                    edValorProduto.setText(c.getString(2));
                    edQuantidadeProduto.setText(c.getString(3));

                }
            }
        });

        diAlterarInformaçoes = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String idProdutos = edIdProduto.getText().toString();
                String nome = edNomeProduto.getText().toString();
                String valor = edValorProduto.getText().toString();
                String quantidade = edQuantidadeProduto.getText().toString();

                if (nome.isEmpty() || valor.isEmpty() || quantidade.isEmpty()) {

                    MostraMensagem("Erro: Todos os campos são obrigatórios");

                } else {

                    Produtos produtos = new Produtos(
                            Integer.parseInt(idProdutos),
                            nome,
                            Double.parseDouble(valor),
                            Double.parseDouble(quantidade)
                    );

                    databaseHelper.alterarProduto(produtos);

                    c = databaseHelper.consultarProdutos();

                    c.moveToPosition(numreg - 1);

                    MostraMensagem("Dados alterados!");
                }
            }
        };

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(EditarActivity.this);

                dialogo.setTitle("Confirmar");
                dialogo.setMessage("Deseja alterar as informações?");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("Sim", diAlterarInformaçoes);
                dialogo.show();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditarActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

    }

    public void MostraMensagem(String str)  {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(EditarActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}