package com.enjoei.vicolmoraes.enjoei.ViewModel;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://private-anon-14a1a92552-enjoeitest.apiary-mock.com/products/home?")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public ProdutosService getTransacoes() {
        return this.retrofit.create(ProdutosService.class);
    }

}
