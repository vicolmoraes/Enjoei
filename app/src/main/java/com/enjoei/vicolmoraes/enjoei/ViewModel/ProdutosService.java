package com.enjoei.vicolmoraes.enjoei.ViewModel;

import com.enjoei.vicolmoraes.enjoei.Model.ProdutosVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProdutosService {
    @GET("home")
    Call<ProdutosVO> buscarProdutos(@Query("page") int pagina);
}
