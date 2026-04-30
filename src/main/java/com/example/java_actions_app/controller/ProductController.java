package com.example.java_actions_app.controller;

import com.example.java_actions_app.infrastructure.entity.Product;
import com.example.java_actions_app.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** 商品一覧コントローラクラス */
@Controller
public class ProductController {

  private final ProductService productService;

  /**
   * コンストラクタ
   *
   * @param productService ProductServiceを注入する
   */
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /** 商品一覧画面を表示する */
  @GetMapping("/products")
  public String listProducts(Model model) {
    // Serviceを呼び出して商品一覧を取得
    List<Product> products = productService.getAllProducts();
    // Viewにデータを渡すため、Modelに登録
    model.addAttribute("products", products);
    // products/list.htmlを利用する
    return "products/list";
  }
}
