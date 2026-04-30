package com.example.java_actions_app.service;

import java.util.List;

import com.example.java_actions_app.infrastructure.entity.Product;
import com.example.java_actions_app.infrastructure.repository.ProductRepository;

/**
 * 商品情報を扱うサービスインターフェイスの実装
 */
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * コンストラクタインジェクション
     * @param productRepository 商品リポジトリを注入する
     */
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /** 
     * 全ての商品情報をID順に取得する 
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllByOrderByIdAsc();
    }
}
