package com.example.java_actions_app.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
/**
 * 計算コントローラークラスのテストクラス
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CalcControllerTest {
    /**
     * MockMvcを注入してコントローラーのテストを行うためのフィールド
     */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("結合テスト: 足し算のPOSTリクエストで、正しく計算結果が画面(Model)に渡されること")
    void testCalculateMvc() throws Exception {
        // フォームからのPOST送信をシミュレート
        mockMvc.perform(post("/calc")
                .param("v1", "10")
                .param("v2", "20")
                .param("op", "+"))
                .andExpect(status().isOk())                         // HTTP 200 OKであること
                .andExpect(view().name("calc"))   // calc.html が呼ばれること
                .andExpect(model().attribute("result", 30)); // 画面に渡すresultが30であること
    }

    @Test
    @DisplayName("結合テスト: ゼロ除算のPOSTリクエストで、エラーメッセージが画面(Model)に渡されること")
    void testCalculateMvcError() throws Exception {
        mockMvc.perform(post("/calc")
                .param("v1", "10")
                .param("v2", "0")
                .param("op", "/"))
                .andExpect(status().isOk())
                .andExpect(view().name("calc"))
                .andExpect(model().attribute("error", "ゼロで割ることはできません"));
    }
}
