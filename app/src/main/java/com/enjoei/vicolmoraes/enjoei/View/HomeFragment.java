package com.enjoei.vicolmoraes.enjoei.View;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.enjoei.vicolmoraes.enjoei.Model.FotoBO;
import com.enjoei.vicolmoraes.enjoei.Model.ProdutoVO;
import com.enjoei.vicolmoraes.enjoei.Model.ProdutosVO;
import com.enjoei.vicolmoraes.enjoei.Model.UsuarioVO;
import com.enjoei.vicolmoraes.enjoei.R;
import com.enjoei.vicolmoraes.enjoei.ViewModel.ControllerSqlite;
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
    private ControllerSqlite crud;
    private Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        paginaAtual = 1;
        crud = new ControllerSqlite(getContext());
        if (isOnline())
            obterProdutos();
        else {
            iniciarAdapter();
            iniciarViews();
        }
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
                crud.deletarRegistros();
                crud.insertProdutos(listaProdutos);
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

    private void iniciarLista() {
        listaProdutos = new ArrayList<>();
        cursor = crud.carregaDados();
        ArrayList<ProdutoVO> listaAux = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                ProdutoVO produtoVO = new ProdutoVO();
                produtoVO.setProdutoId(cursor.getInt(0));
                produtoVO.setTitle(cursor.getString(1));
                produtoVO.setPrice(cursor.getInt(2));
                produtoVO.setOriginal_price(cursor.getInt(3));
                produtoVO.setLikes_count(cursor.getInt(4));
                produtoVO.setContent(cursor.getString(5));
                produtoVO.setPublished_comments_count(cursor.getInt(6));
                FotoBO fotoBO = new FotoBO();
                fotoBO.setPublic_id(cursor.getString(7));
                fotoBO.setCrop(cursor.getString(8));
                fotoBO.setGravity(cursor.getString(9));
                ArrayList<FotoBO> fotos = new ArrayList<>();
                fotos.add(fotoBO);
                produtoVO.setPhotos(fotos);
                UsuarioVO usuarioVO = new UsuarioVO();
                usuarioVO.setName(cursor.getString(10));
                FotoBO avatar = new FotoBO();
                avatar.setPublic_id(cursor.getString(11));
                avatar.setCrop(cursor.getString(12));
                avatar.setGravity(cursor.getString(13));
                usuarioVO.setAvatar(avatar);
                produtoVO.setUser(usuarioVO);

                listaAux.add(produtoVO);
            } while (cursor.moveToNext());
        }

        listaProdutos = listaAux;
    }

    private void iniciarAdapter() {
        if (listaProdutos == null) iniciarLista();
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
//        final ScrollView scroll = view.findViewById(R.id.scroll);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            scroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                @Override
//                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                    int g = scroll.getMaxScrollAmount();
//
//                    if (scrollY >= scroll.getMaxScrollAmount()) {
//                        if (paginaAtual < paginaTotal) {
//                            Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
//                            new Handler().postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    paginaAtual++;
//                                    obterProdutos();
//                                }
//                            }, 5000);
//                        }
//                    }
//                }
//            });
//        }
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
                pullToRefresh.setRefreshing(false);
            }
        });

    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
