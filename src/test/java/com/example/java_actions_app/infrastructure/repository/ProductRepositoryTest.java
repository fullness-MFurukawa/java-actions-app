package com.example.java_actions_app.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.java_actions_app.infrastructure.entity.Product;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/** ProductRepositoryのデータベース連動テスト */
// テスト実行時にHibernateが自動でテーブルを作成するよう設定
@Disabled("一時的にテストを無効化")
@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=update")
@Testcontainers
public class ProductRepositoryTest {
  // TestcontainersのPostgreSQLコンテナを定義
  // @ServiceConnectionにより、接続情報がSpringに自動注入される（設定ファイル不要！）
  @Container @ServiceConnection
  static PostgreSQLContainer<?> postgres =
      new PostgreSQLContainer<>("postgres:17-alpine").withDatabaseName("actions_db");

  @Autowired private ProductRepository productRepository;

  @Autowired private JdbcTemplate jdbcTemplate;

  /** テストごとの初期化（データ投入） */
  @BeforeEach
  void setUp() {
    // テスト前にデータをクリア
    productRepository.deleteAll();

    // 初期データ投入のSQL
    String sql =
        "INSERT INTO product (product_uuid, name, price) VALUES "
            + "('ac413f22-0cf1-490a-9635-7e9ca810e544','水性ボールペン(黒)',120), "
            + "('8f81a72a-58ef-422b-b472-d982e8665292','水性ボールペン(赤)',120), "
            + "('d952b98c-a1ea-478d-8380-3b90fde872ea','水性ボールペン(青)',120), "
            + "('9959e553-c9da-4646-bd85-8663a3541583','油性ボールペン(黒)',100), "
            + "('79023e82-9197-40a5-b236-26487f404be4','油性ボールペン(赤)',100), "
            + "('7dfd0fd0-0893-4d20-83ef-6f70aab0ab76','油性ボールペン(青)',100), "
            + "('dc7243af-c2ce-4136-bd5d-c6b28ee0a20a','蛍光ペン(黄)',130), "
            + "('83fbc81d-2498-4da6-b8c2-54878d3b67ff','蛍光ペン(赤)',130), "
            + "('ee4b3752-3fbd-45fc-afb5-8f37c3f701c9','蛍光ペン(青)',130), "
            + "('35cb51a7-df79-4771-9939-7f32c19bca45','蛍光ペン(緑)',130), "
            + "('e4850253-f363-4e79-8110-7335e4af45be','鉛筆(黒)',100), "
            + "('5ca7dbdf-0010-44c5-a001-e4c13c4fe3a1','鉛筆(赤)',100), "
            + "('fbc43b9b-90a9-4712-925c-4d66a2a30372','色鉛筆(12色)',400), "
            + "('4b3db238-8ada-49b4-bb60-1a034914e528','色鉛筆(48色)',1300);";
    jdbcTemplate.execute(sql);
  }

  @Test
  @DisplayName("商品一覧取得メソッドのテスト")
  void testFindAllByOrderByIdAsc() {
    // 実行 (Act)
    List<Product> products = productRepository.findAllByOrderByIdAsc();

    // 視覚的確認のためのログ出力
    System.out.println("--- 取得した商品データ一覧 ---");
    for (Product p : products) {
      System.out.println(
          "ID: " + p.getId() + ", Name: " + p.getName() + ", Price: " + p.getPrice() + "円");
    }
    System.out.println("------------------------------");

    // 検証 (Assert)
    assertEquals(14, products.size());
    assertEquals("水性ボールペン(黒)", products.get(0).getName());
    assertEquals(1300, products.get(13).getPrice());
  }
}
