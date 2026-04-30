package com.example.java_actions_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.java_actions_app.infrastructure.entity.Product;
import com.example.java_actions_app.infrastructure.repository.ProductRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** 商品情報を扱うサービスインターフェイス実装のテストドライバ Mockを使用して、ProductServiceImplのテストを行う */
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
  // モック化する依存オブジェクト
  @Mock private ProductRepository productRepository;

  // モックを注入するテスト対象オブジェクト
  @InjectMocks private ProductServiceImpl productService;

  @Test
  @DisplayName("商品一覧取得サービスメソッドのテスト")
  void testGetAllProducts() {
    // 準備 (Arrange)
    Product p1 = new Product();
    p1.setId(1);
    p1.setName("水性ボールペン(黒)");
    p1.setPrice(120);

    Product p2 = new Product();
    p2.setId(2);
    p2.setName("色鉛筆(48色)");
    p2.setPrice(1300);

    // モックの振る舞いを定義：findAllByOrderByIdAsc() が呼ばれたらダミーリストを返す
    when(productRepository.findAllByOrderByIdAsc()).thenReturn(Arrays.asList(p1, p2));

    // 実行
    List<Product> products = productService.getAllProducts();
    // 検証
    assertEquals(2, products.size());
    assertEquals("水性ボールペン(黒)", products.get(0).getName());
    // productRepositoryのfindAllByOrderByIdAsc()が正確に1回呼ばれたかを検証
    verify(productRepository, times(1)).findAllByOrderByIdAsc();
  }
}
