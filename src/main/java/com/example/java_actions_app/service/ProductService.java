package com.example.java_actions_app.service;

import com.example.java_actions_app.infrastructure.entity.Product;
import java.util.List;

/** 商品情報を扱うサービスインターフェイス */
public interface ProductService {
  /** 全ての商品情報をID順に取得する */
  List<Product> getAllProducts();
}
