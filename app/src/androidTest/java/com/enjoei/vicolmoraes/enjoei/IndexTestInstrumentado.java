package com.enjoei.vicolmoraes.enjoei;


import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.enjoei.vicolmoraes.enjoei.Model.ProdutoVO;
import com.enjoei.vicolmoraes.enjoei.View.IndexActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.NumberFormat;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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
            try {
                mActivityRule.wait(5000);

            } catch (Exception c) {

            }
            //Verifica
            onView(withId(R.id.rv_home_produtos))
                    .check(matches(hasDescendant(
                            allOf(
                                    withId(R.id.tv_produto_item_valor),
                                    withText(NumberFormat.getCurrencyInstance().format(produtoVO.getPrice()))
                            ))));

            onView(withId(R.id.rv_home_produtos))
                    .check(matches(hasDescendant(
                            allOf(
                                    withId(R.id.tv_produto_item_titulo),
                                    withText(produtoVO.getTitle())
                            ))));

            onView(withId(R.id.rv_home_produtos))
                    .check(matches(hasDescendant(
                            allOf(
                                    withId(R.id.tv_produto_icone_like),
                                    withText(String.valueOf(produtoVO.getLikes_count()))
                            ))));
        }
    }

    @Test
    public void abreProduto_verificaExibicaoProduto() {

        synchronized (mActivityRule) {
            //Prepara
            Intent intent = new Intent();
            mActivityRule.launchActivity(intent);
            mActivityRule.notify();
            try {
                mActivityRule.wait(5000);

            } catch (Exception c) {

            }
            //Executa
            onView(withId(R.id.rv_home_produtos))
                    .perform(actionOnItemAtPosition(0, click()));
            try {
                mActivityRule.wait(5000);

            } catch (Exception c) {

            }
            //Verifica
            onView(withId(R.id.tv_produto_titulo)).check(matches(withText(produtoVO.getTitle())));
            onView(withId(R.id.tv_produto_descricao)).check(matches(withText(produtoVO.getContent())));
            onView(withId(R.id.tv_produto_valor_atual)).check(matches(withText(NumberFormat.getCurrencyInstance().format(produtoVO.getPrice()))));
            onView(withId(R.id.iv_produto_comentarios_numero)).check(matches(withText(String.valueOf(produtoVO.getPublished_comments_count()))));
        }
    }
}
