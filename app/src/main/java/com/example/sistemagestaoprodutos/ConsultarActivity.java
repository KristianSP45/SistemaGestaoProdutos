package com.example.sistemagestaoprodutos;

import android.app.AlertDialog;
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

public class ConsultarActivity extends AppCompatActivity {

    EditText edIdProduto, edNomeProduto, edValorProduto, edQuantidadeProduto;

    Button btnVoltar;

    DatabaseHelper databaseHelper;

    ImageView imgPrimeiro, imgAnterior, imgProximo, imgUltimo;

    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consultar);

        databaseHelper = new DatabaseHelper(this);

        edIdProduto = findViewById(R.id.edIdProduto);
        edNomeProduto = findViewById(R.id.edNomeProduto);
        edValorProduto = findViewById(R.id.edValorProduto);
        edQuantidadeProduto = findViewById(R.id.edQuantidadeProduto);

        btnVoltar = findViewById(R.id.btnVoltar);

        imgPrimeiro = (ImageView) findViewById(R.id.imgPrimeiro);
        imgAnterior = (ImageView) findViewById(R.id.imgAnterior);
        imgProximo = (ImageView) findViewById(R.id.imgProximo);
        imgUltimo = (ImageView) findViewById(R.id.imgUltimo);

        c = databaseHelper.consultarProdutos();

        if(c != null && c.moveToFirst()){

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

                    edIdProduto.setText(c.getString(0));
                    edNomeProduto.setText(c.getString(1));
                    edValorProduto.setText(c.getString(2));
                    edQuantidadeProduto.setText(c.getString(3));

                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConsultarActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

    }

    public void MostraMensagem(String str)  {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(ConsultarActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}