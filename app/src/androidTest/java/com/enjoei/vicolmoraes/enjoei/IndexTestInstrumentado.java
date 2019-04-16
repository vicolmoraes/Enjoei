package com.enjoei.vicolmoraes.enjoei;


import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.enjoei.vicolmoraes.enjoei.Model.ProdutoVO;
import com.enjoei.vicolmoraes.enjoei.View.IndexActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.NumberFormat;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IndexTestInstrumentado {
    @Rule
    public final ActivityTestRule<IndexActivity> mActivityRule = new ActivityTestRule<>(
            IndexActivity.class);
    private ProdutoVO produtoVO;

    @Before
    public void gerarPrutoComparacao() {
        produtoVO = new ProdutoVO();
        produtoVO.setLikes_count(46);
        produtoVO.setContent("tenis roxo, pouco usado, tamanho 36");
        produtoVO.setTitle("Tenis maneiro");
        produtoVO.setPrice(400);
        produtoVO.setOriginal_price(400);
        produtoVO.setPublished_comments_count(15);
    }

    @Test
    public void abreHome_verificaExibicaoProduto() {

        synchronized (mActivityRule) {
            //Prepara
            Intent intent = new Intent();
            mActivityRule.launchActivity(intent);
            mActivityRule.notify();
            RecyclerView re = mActivityRule.getActivity().findViewById(R.id.rv_home_produtos);
            TextView textoTitulo = re.getChildAt(0).findViewById(R.id.tv_produto_item_titulo);
            TextView valor = re.getChildAt(0).findViewById(R.id.tv_produto_item_valor);
            TextView likes = re.getChildAt(0).findViewById(R.id.tv_produto_icone_like);

            //Verifica
            Assert.assertEquals(NumberFormat.getCurrencyInstance().format(produtoVO.getPrice()), valor.getText().toString());
            Assert.assertEquals(produtoVO.getTitle(), textoTitulo.getText().toString());
            Assert.assertEquals(String.valueOf(produtoVO.getLikes_count()), likes.getText().toString());
        }
    }

    @Test
    public void abreProduto_verificaExibicaoProduto() {

        synchronized (mActivityRule) {
            //Prepara
            Intent intent = new Intent();
            mActivityRule.launchActivity(intent);
            mActivityRule.notify();
            RecyclerView re = mActivityRule.getActivity().findViewById(R.id.rv_home_produtos);
            TextView texto = re.getChildAt(0).findViewById(R.id.tv_produto_item_titulo);
            texto.performClick();
            TextView titulo = mActivityRule.getActivity().findViewById(R.id.tv_produto_titulo);
            TextView descricao = mActivityRule.getActivity().findViewById(R.id.tv_produto_descricao);
            TextView valor = mActivityRule.getActivity().findViewById(R.id.tv_produto_valor_atual);
            TextView comentarios = mActivityRule.getActivity().findViewById(R.id.iv_produto_comentarios_numero);

            //Verifica
            Assert.assertEquals(NumberFormat.getCurrencyInstance().format(produtoVO.getPrice()), valor.getText().toString());
            Assert.assertEquals(produtoVO.getTitle(), titulo.getText().toString());
            Assert.assertEquals(produtoVO.getContent(), descricao.getText().toString());
            Assert.assertEquals(String.valueOf(produtoVO.getPublished_comments_count()), comentarios.getText().toString());

        }
    }
}
