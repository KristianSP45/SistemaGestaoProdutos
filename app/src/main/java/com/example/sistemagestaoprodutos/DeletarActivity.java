package com.example.sistemagestaoprodutos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

public class DeletarActivity extends AppCompatActivity {

    EditText edIdProduto, edNomeProduto, edValorProduto, edQuantidadeProduto;

    Button btnVoltar, btnExcluirProduto;

    DatabaseHelper databaseHelper;

    ImageView imgPrimeiro, imgAnterior, imgProximo, imgUltimo;

    Cursor c;

    int numreg;

    DialogInterface.OnClickListener diDeletarRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deletar);

        databaseHelper = new DatabaseHelper(this);

        edIdProduto = findViewById(R.id.edIdProduto);
        edNomeProduto = findViewById(R.id.edNomeProduto);
        edValorProduto = findViewById(R.id.edValorProduto);
        edQuantidadeProduto = findViewById(R.id.edQuantidadeProduto);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnExcluirProduto = findViewById(R.id.btnExcluirProduto);

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

        diDeletarRegistros = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String idProdutos = edIdProduto.getText().toString();

                databaseHelper.deletarProduto(idProdutos);

                c = databaseHelper.consultarProdutos();

                c.moveToFirst();

                edIdProduto.setText(c.getString(0));
                edNomeProduto.setText(c.getString(1));
                edValorProduto.setText(c.getString(2));
                edQuantidadeProduto.setText(c.getString(3));

                MostraMensagem("Dados deletados!");
            }
        };

        btnExcluirProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(DeletarActivity.this);

                dialogo.setTitle("Confirmar");
                dialogo.setMessage("Deseja alterar as informações?");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("Sim", diDeletarRegistros);
                dialogo.show();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DeletarActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

    }

    public void MostraMensagem(String str)  {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(DeletarActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}