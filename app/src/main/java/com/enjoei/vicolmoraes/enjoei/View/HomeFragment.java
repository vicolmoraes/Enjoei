package com.enjoei.vicolmoraes.enjoei.View;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.enjoei.vicolmoraes.enjoei.Model.ProdutoVO;
import com.enjoei.vicolmoraes.enjoei.Model.ProdutosVO;
import com.enjoei.vicolmoraes.enjoei.R;
import com.enjoei.vicolmoraes.enjoei.ViewModel.ProdutosAdapter;
import com.enjoei.vicolmoraes.enjoei.ViewModel.RetrofitConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String PRODUTO = "produto";

    private RecyclerView rvProdutos;
    private View view;
    private ProdutosAdapter adapter;
    private ArrayList<ProdutoVO> listaProdutos;
    private int paginaAtual;
    private int paginaTotal;
    private SwipeRefreshLayout pullToRefresh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        paginaAtual = 1;
        obterProdutos();
        return view;
    }

    private void obterProdutos() {
        Call<ProdutosVO> call = new RetrofitConfig().getTransacoes().buscarProdutos(paginaAtual);
        call.enqueue(new Callback<ProdutosVO>() {
            @Override
            public void onResponse(@NonNull Call<ProdutosVO> call, @NonNull Response<ProdutosVO> response) {
                ProdutosVO data = response.body();
                assert data != null;
                paginaTotal = data.getPagination().getTotal_pages();
                if (listaProdutos == null)
                    listaProdutos = data.getProducts();
                else listaProdutos.addAll(data.getProducts());
                iniciarAdapter();
                iniciarViews();
                Log.e("ERRRO", listaProdutos.get(0).getTitle());
            }

            @Override
            public void onFailure(@NonNull Call<ProdutosVO> call, @NonNull Throwable t) {
                IndexActivity activity = (IndexActivity) getActivity();
                Intent intentErro = new Intent(getContext(), ErroActivity.class);
                activity.startActivity(intentErro);
            }
        });
    }

    private void iniciarAdapter() {
        ProdutosAdapter.ItemClickListener mClickListener = new ProdutosAdapter.ItemClickListener() {

            @Override
            public void click(int posicao) {
                IndexActivity activity = (IndexActivity) getActivity();
                ProdutoFragment produto = new ProdutoFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(PRODUTO, listaProdutos.get(posicao));
                produto.setArguments(bundle);
                activity.iniciarFragments(produto);
            }
        };

        adapter = new ProdutosAdapter(listaProdutos, mClickListener);
    }

    private void iniciarViews() {
        rvProdutos = view.findViewById(R.id.rv_home_produtos);
        rvProdutos.setAdapter(adapter);
        pullToRefresh = view.findViewById(R.id.pullToRefresh);
        final ScrollView scroll = view.findViewById(R.id.scroll);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    int g = scroll.getMaxScrollAmount();

                    if (scrollY >= scroll.getMaxScrollAmount()) {
                        if (paginaAtual < paginaTotal) {
                            Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    paginaAtual++;
                                    obterProdutos();
                                }
                            }, 5000);
                        }
                    }
                }
            });
        }
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
                pullToRefresh.setRefreshing(false);
            }
        });

    }

}
