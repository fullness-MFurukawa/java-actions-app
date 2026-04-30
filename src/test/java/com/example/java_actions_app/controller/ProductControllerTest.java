package com.example.java_actions_app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.java_actions_app.infrastructure.entity.Product;
import com.example.java_actions_app.service.ProductService;

/**
 * ProductControllerのみを対象とした軽量なWeb層テスト
 */
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc; 
    @Autowired
    private ProductService productService; // 下記で作成したServiceのモックが注入される

    @TestConfiguration
    static class MockConfig {
        @Bean
        public ProductService productService() {
            return Mockito.mock(ProductService.class);
        }
    }

    @Test
    @DisplayName("商品一覧画面の表示テスト（@MockBean不使用版）")
    void testListProducts() throws Exception {
        // 準備 (Arrange) : モック化されたRepositoryが返すダミーデータを設定
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("水性ボールペン(黒)");
        p1.setPrice(120);
        List<Product> mockProducts = Arrays.asList(p1);

        when(productService.getAllProducts()).thenReturn(mockProducts);

        // 実行 & 検証 (Act & Assert)
        mockMvc.perform(get("/products")) // "/products" へGETリクエストを送信
               .andExpect(status().isOk()) // HTTPステータスが200OKであること
               .andExpect(view().name("products/list")) // 呼び出されるHTMLテンプレート名が正しいこと
               .andExpect(model().attributeExists("products")) // Modelに "products" がセットされていること
               .andExpect(model().attribute("products", mockProducts)); // Modelの中身が一致していること
    }
}
