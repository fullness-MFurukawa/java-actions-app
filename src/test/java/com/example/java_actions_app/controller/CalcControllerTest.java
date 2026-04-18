package com.example.java_actions_app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.java_actions_app.service.CalcService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

/** 計算コントローラークラスのテストクラス */
// @SpringBootTest
// @AutoConfigureMockMvc
@WebMvcTest(CalcController.class) // Web層（コントローラー）のみを起動する（JPAを起動しない）
@Import(CalcService.class) // コントローラーが依存するServiceを明示的に読み込む
public class CalcControllerTest {
  /** MockMvcを注入してコントローラーのテストを行うためのフィールド */
  @Autowired private MockMvc mockMvc;

  @Test
  @DisplayName("結合テスト: 足し算のPOSTリクエストで、正しく計算結果が画面(Model)に渡されること")
  void testCalculateMvc() throws Exception {
    // フォームからのPOST送信をシミュレート
    mockMvc
        .perform(post("/calc").param("v1", "10").param("v2", "20").param("op", "+"))
        .andExpect(status().isOk()) // HTTP 200 OKであること
        .andExpect(view().name("calc")) // calc.html が呼ばれること
        .andExpect(model().attribute("result", 30)); // 画面に渡すresultが30であること
  }

  @Test
  @DisplayName("結合テスト: ゼロ除算のPOSTリクエストで、エラーメッセージが画面(Model)に渡されること")
  void testCalculateMvcError() throws Exception {
    mockMvc
        .perform(post("/calc").param("v1", "10").param("v2", "0").param("op", "/"))
        .andExpect(status().isOk())
        .andExpect(view().name("calc"))
        .andExpect(model().attribute("error", "ゼロで割ることはできません"));
  }
}
