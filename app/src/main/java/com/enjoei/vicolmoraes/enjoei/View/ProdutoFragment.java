package com.enjoei.vicolmoraes.enjoei.View;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enjoei.vicolmoraes.enjoei.Model.FotoBO;
import com.enjoei.vicolmoraes.enjoei.Model.ProdutoVO;
import com.enjoei.vicolmoraes.enjoei.R;
import com.enjoei.vicolmoraes.enjoei.ViewModel.ViewPagerAdapter;
import com.enjoei.vicolmoraes.enjoei.ViewModel.WrapContentViewPager;
import com.viewpagerindicator.CirclePageIndicator;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ProdutoFragment extends Fragment {
    private static final String PRODUTO = "produto";
    private WrapContentViewPager viewPager;
    private ProdutoVO produtoVO;
    private TextView valor;
    private TextView valorAntigo;
    private TextView comentarios;
    private TextView titulo;
    private TextView descricao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.produto_fragment, container, false);

        recuperarInformacoes();
        iniciarViews(view);
        return view;
    }

    private void recuperarInformacoes() {
        Bundle args = getArguments();
        assert args != null;
        produtoVO = (ProdutoVO) args.getSerializable(PRODUTO);
    }

    private void iniciarViews(View view) {
        valor = view.findViewById(R.id.tv_produto_valor_atual);
        valorAntigo = view.findViewById(R.id.tv_produto_valor_antigo);
        comentarios = view.findViewById(R.id.iv_produto_comentarios_numero);
        titulo = view.findViewById(R.id.tv_produto_titulo);
        descricao = view.findViewById(R.id.tv_produto_descricao);
        viewPager = view.findViewById(R.id.sl_produto_slider);

        valor.setText(NumberFormat.getCurrencyInstance().format(produtoVO.getPrice()));
        valorAntigo.setText(NumberFormat.getCurrencyInstance().format(produtoVO.getOriginal_price()));
        valorAntigo.setPaintFlags(valorAntigo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        comentarios.setText(String.valueOf(produtoVO.getPublished_comments_count()));
        titulo.setText(produtoVO.getTitle());
        descricao.setText(produtoVO.getContent());

        ArrayList<String> list = new ArrayList<String>();
        for (FotoBO url : produtoVO.getPhotos()) {
            list.add(url.gerarUrl());
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getContext(), list);
        viewPager.setAdapter(adapter);
        CirclePageIndicator circleIndicator = (CirclePageIndicator) view.findViewById(R.id.sl_produto_pager);
        circleIndicator.setViewPager(viewPager);
    }
}