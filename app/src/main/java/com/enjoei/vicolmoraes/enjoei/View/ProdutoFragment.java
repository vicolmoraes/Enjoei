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
    private ProdutoVO produtoVO;

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
        TextView tvValor = view.findViewById(R.id.tv_produto_valor_atual);
        TextView tvValorAntigo = view.findViewById(R.id.tv_produto_valor_antigo);
        TextView tvComentarios = view.findViewById(R.id.iv_produto_comentarios_numero);
        TextView tvTitulo = view.findViewById(R.id.tv_produto_titulo);
        TextView tvDescricao = view.findViewById(R.id.tv_produto_descricao);
        WrapContentViewPager vpSlideFotos = view.findViewById(R.id.vp_produto_slider);

        tvValor.setText(NumberFormat.getCurrencyInstance().format(produtoVO.getPrice()));
        tvValorAntigo.setText(NumberFormat.getCurrencyInstance().format(produtoVO.getOriginal_price()));
        tvValorAntigo.setPaintFlags(tvValorAntigo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvComentarios.setText(String.valueOf(produtoVO.getPublished_comments_count()));
        tvTitulo.setText(produtoVO.getTitle());
        tvDescricao.setText(produtoVO.getContent());

        ArrayList<String> list = new ArrayList<>();
        for (FotoBO url : produtoVO.getPhotos()) {
            list.add(url.gerarUrl());
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getContext(), list);
        vpSlideFotos.setAdapter(adapter);
        CirclePageIndicator circleIndicator = view.findViewById(R.id.sl_produto_pager);
        circleIndicator.setViewPager(vpSlideFotos);
    }
}