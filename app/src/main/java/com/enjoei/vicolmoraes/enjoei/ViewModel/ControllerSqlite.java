package com.enjoei.vicolmoraes.enjoei.ViewModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.enjoei.vicolmoraes.enjoei.Model.ProdutoVO;

import java.util.ArrayList;

public class ControllerSqlite {

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

    private SQLiteDatabase db;
    private ProdutoMinimoSqlite banco;
    private Context contexto;

    public ControllerSqlite(Context context) {
        banco = new ProdutoMinimoSqlite(context);
        contexto = context;
    }

    public String insereDadoComId(ProdutoVO produtoVO) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(ID, produtoVO.getProdutoId());
        valores.put(TITLE, produtoVO.getTitle());
        valores.put(PRICE, produtoVO.getPrice());
        valores.put(ORIGINAL_PRICE, produtoVO.getOriginal_price());
        valores.put(LIKES_COUNT, produtoVO.getLikes_count());
        valores.put(CONTENT, produtoVO.getContent());
        valores.put(PUBLISHED_COMMENTS_COUNT, produtoVO.getPublished_comments_count());
        valores.put(PHOTO1, produtoVO.getPhotos().get(0).getPublic_id());
        valores.put(PHOTO2, produtoVO.getPhotos().get(0).getCrop());
        valores.put(PHOTO3, produtoVO.getPhotos().get(0).getGravity());
        valores.put(USER_NAME, produtoVO.getUser().getName());
        valores.put(USER_AVATAR1, produtoVO.getUser().getAvatar().getPublic_id());
        valores.put(USER_AVATAR2, produtoVO.getUser().getAvatar().getCrop());
        valores.put(USER_AVATAR3, produtoVO.getUser().getAvatar().getGravity());

        resultado = db.insert(TABELA_PRODUTO_MINIMO, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else {
            return "Registro Inserido com sucesso ";
        }
    }

    public void insertProdutos(ArrayList<ProdutoVO> listaProdutos) {
        for (ProdutoVO produtoVO : listaProdutos) {
            insereDadoComId(produtoVO);
        }
    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {ID, TITLE, PRICE, ORIGINAL_PRICE, LIKES_COUNT, CONTENT, PUBLISHED_COMMENTS_COUNT, PHOTO1, PHOTO2, PHOTO3, USER_NAME, USER_AVATAR1, USER_AVATAR2, USER_AVATAR3};
        db = banco.getReadableDatabase();

        if (!banco.doesDatabaseExist(contexto)) {
            banco.onCreate(db);
        }

        cursor = db.query(TABELA_PRODUTO_MINIMO, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void deletarRegistros() {
        db = banco.getReadableDatabase();
        db.delete(TABELA_PRODUTO_MINIMO, null, null);
        db.close();
    }
}
