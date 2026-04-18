package com.example.java_actions_app.infrastructure.repository;

import com.example.java_actions_app.infrastructure.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** 商品情報のデータアクセスを担うリポジトリインターフェイス */
public interface ProductRepository extends JpaRepository<Product, Integer> {

  /** 全ての商品をID順に取得する */
  List<Product> findAllByOrderByIdAsc();
}
