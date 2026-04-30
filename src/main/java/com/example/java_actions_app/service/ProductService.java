package com.example.java_actions_app.service;

import java.util.List;

import com.example.java_actions_app.infrastructure.entity.Product;

/**
 * 商品情報を扱うサービスインターフェイス
 */
public interface ProductService {
    /** 
     * 全ての商品情報をID順に取得する 
     */
    List<Product> getAllProducts();
}
