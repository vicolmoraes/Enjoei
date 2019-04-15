package com.enjoei.vicolmoraes.enjoei.ViewModel;

import com.enjoei.vicolmoraes.enjoei.Model.ProdutosVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProdutosService {
    @GET("page={pagina}")
    Call<ProdutosVO> buscarProdutos(@Path("pagina") String pagina);
}
