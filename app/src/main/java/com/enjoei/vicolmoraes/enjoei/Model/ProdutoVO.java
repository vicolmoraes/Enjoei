package com.enjoei.vicolmoraes.enjoei.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoVO {
    @JsonProperty("id")
    private int produtoId;

    @JsonProperty("discount_percentage")
    private int discount_percentage;

    @JsonProperty("photos")
    private ArrayList<FotoBO> photos;

    @JsonProperty("title")
    private String title;

    @JsonProperty("price")
    private int price;

    @JsonProperty("original_price")
    private int original_price;

    @JsonProperty("size")
    private String size;

    @JsonProperty("likes_count")
    private int likes_count;

    @JsonProperty("maximum_installment")
    private int maximum_installment;

    @JsonProperty("published_comments_count")
    private int published_comments_count;

    @JsonProperty("content")
    private String content;

    @JsonProperty("user")
    private UsuarioVO user;

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(int discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public ArrayList<FotoBO> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<FotoBO> photos) {
        this.photos = photos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getMaximum_installment() {
        return maximum_installment;
    }

    public void setMaximum_installment(int maximum_installment) {
        this.maximum_installment = maximum_installment;
    }

    public int getPublished_comments_count() {
        return published_comments_count;
    }

    public void setPublished_comments_count(int published_comments_count) {
        this.published_comments_count = published_comments_count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UsuarioVO getUser() {
        return user;
    }

    public void setUser(UsuarioVO user) {
        this.user = user;
    }
}
