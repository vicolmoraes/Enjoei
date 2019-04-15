package com.enjoei.vicolmoraes.enjoei.Model;

import java.util.ArrayList;

public class ProdutoVO {
    int produtoId;
    int discount_percentage;
    ArrayList<FotoVO> photos;
    String title;
    int price;
    int original_price;
    String size;
    int likes_count;
    int maximum_installment;
    int published_comments_count;
    String content;
    UsuarioVO user;
}
