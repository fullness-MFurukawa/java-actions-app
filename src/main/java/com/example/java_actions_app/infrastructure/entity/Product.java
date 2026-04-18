package com.example.java_actions_app.infrastructure.entity;

import jakarta.persistence.*;
import java.util.UUID;

/** 商品を扱うエンティティクラス */
@Entity
@Table(name = "product")
public class Product {
  /** ID（自動生成） */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /** 商品ID */
  @Column(name = "product_uuid", nullable = false, unique = true)
  private UUID productUuid = UUID.randomUUID();

  /** 商品名 */
  @Column(name = "name", length = 30)
  private String name;

  /** 価格 */
  @Column(name = "price")
  private Integer price;

  // Getter と Setter
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public UUID getProductUuid() {
    return productUuid;
  }

  public void setProductUuid(UUID productUuid) {
    this.productUuid = productUuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }
}
