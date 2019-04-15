package com.enjoei.vicolmoraes.enjoei.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private RecyclerView rvProdutos;
    View view;
    private ProdutosAdapter adapter;
    private ArrayList<ProdutoVO> listaProdutos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);

        obterProdutos();
        return view;
    }

    private void obterProdutos() {
        Call<ProdutosVO> call = new RetrofitConfig().getTransacoes().buscarProdutos(1);
        call.enqueue(new Callback<ProdutosVO>() {
            @Override
            public void onResponse(@NonNull Call<ProdutosVO> call, @NonNull Response<ProdutosVO> response) {
                ProdutosVO data = response.body();
                assert data != null;
                listaProdutos = data.getProducts();
                iniciarAdapter();
                iniciarViews();
                Log.e("ERRRO", listaProdutos.get(0).getTitle());

            }

            @Override
            public void onFailure(@NonNull Call<ProdutosVO> call, @NonNull Throwable t) {
                Log.e("ERRRO", t.getMessage());

            }
        });
    }

    private void iniciarAdapter() {
        ProdutosAdapter.ItemClickListener mClickListener = new ProdutosAdapter.ItemClickListener() {

            @Override
            public void click(int posicao) {

            }
        };

        adapter = new ProdutosAdapter(listaProdutos, mClickListener);
    }

    private void iniciarViews() {
        rvProdutos = view.findViewById(R.id.rv_home_produtos);
        rvProdutos.setAdapter(adapter);
    }
}
