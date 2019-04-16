package com.enjoei.vicolmoraes.enjoei.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutosVO {
    @JsonProperty("products")
    private ArrayList<ProdutoVO> products;

    @JsonProperty("pagination")
    private PaginacaoVO pagination;

    public PaginacaoVO getPagination() {
        return pagination;
    }

    public void setPagination(PaginacaoVO pagination) {
        this.pagination = pagination;
    }

    public ArrayList<ProdutoVO> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProdutoVO> products) {
        this.products = products;
    }
}
