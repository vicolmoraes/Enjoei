package com.enjoei.vicolmoraes.enjoei.ViewModel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

public class ProdutoMinimoSqlite extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final int VERSAO = 1;
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String PRICE = "price";
    private static final String ORIGINAL_PRICE = "original_price";
    private static final String LIKES_COUNT = "likes_count";
    private static final String PUBLISHED_COMMENTS_COUNT = "published_comments_count";
    private static final String CONTENT = "content";
    private static final String PHOTO1 = "photo1";
    private static final String PHOTO2 = "photo2";
    private static final String PHOTO3 = "photo3";
    private static final String USER_NAME = "user_name";
    private static final String USER_AVATAR1 = "user_avatar1";
    private static final String USER_AVATAR2 = "user_avatar2";
    private static final String USER_AVATAR3 = "user_avatar3";

    private static final String TABELA_PRODUTO_MINIMO = "tabela_produto_minimo";

    public ProdutoMinimoSqlite(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA_PRODUTO_MINIMO + " ( "
                + ID + " integer,"
                + TITLE + " text,"
                + PRICE + " integer,"
                + ORIGINAL_PRICE + " integer,"
                + LIKES_COUNT + " integer,"
                + PUBLISHED_COMMENTS_COUNT + " integer,"
                + CONTENT + " text,"
                + PHOTO1 + " text,"
                + PHOTO2 + " text,"
                + PHOTO3 + " text,"
                + USER_NAME + " text,"
                + USER_AVATAR1 + " text,"
                + USER_AVATAR2 + " text,"
                + USER_AVATAR3 + " text"
                + " );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRODUTO_MINIMO);
        onCreate(db);
    }

    public boolean doesDatabaseExist(Context context) {
        File dbFile = context.getDatabasePath(NOME_BANCO);
        return dbFile.exists();
    }
}
