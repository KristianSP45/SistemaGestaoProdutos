package com.example.sistemagestaoprodutos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "banco.db";
    private static final String TABELA_USUARIO = "usuario";
    private static final String TABELA_PRODUTOS = "produtos";
    private static final int DATABASE_VERSION = 3;

    //Usuario
    private static String idUser = "idUser";
    private static String email = "email";
    private static String senha = "senha";

    //Produtos
    private static String idProdutos = "idProdutos";
    private static String nome = "nome";
    private static String valor = "valor";
    private static String quantidade = "quantidade";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tabelaUsuario =
                "create table " + TABELA_USUARIO +
                        "(idUser INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "email text," +
                        "senha text)";

        String tabelaProdutos =
                "create table " + TABELA_PRODUTOS +
                        "(idProdutos INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nome text," +
                        "valor real," +
                        "quantidade integer)";

        db.execSQL(tabelaUsuario);
        db.execSQL(tabelaProdutos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRODUTOS);

        onCreate(db);
    }

    public void adicionarUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(email, usuario.getEmail());
        values.put(senha, usuario.getSenha());
        db.insert(TABELA_USUARIO, null, values);
    }

    public Boolean verificarEmailExiste(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuario where email = ?", new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

    public Boolean verificarEmailSenha(String email, String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuario where email = ? and senha = ?", new String[]{email, senha});

        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

    public void adicionarProduto(Produtos produtos){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(nome, produtos.getNome());
        values.put(valor, produtos.getValor());
        values.put(quantidade, produtos.getQuantidade());

        db.insert(TABELA_PRODUTOS, null, values);
    }

    public Cursor consultarProdutos(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "produtos",
                new String[]{
                        "idProdutos", "nome", "valor", "quantidade"
                }, null, null, null, null, null
        );

        return cursor;
    }

    public void alterarProduto(Produtos produtos){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(nome, produtos.getNome());
        values.put(valor, produtos.getValor());
        values.put(quantidade, produtos.getQuantidade());

        db.update(
                TABELA_PRODUTOS,
                values,
                "idProdutos = ?",
                new String[]{String.valueOf(produtos.getIdProdutos())}
        );
    }

    public void deletarProduto (String idProdutos){

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from " + TABELA_PRODUTOS + " where idProdutos = ?", new String[]{idProdutos});
    }
}
