package com.example.java_actions_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 計算サービスクラスの単体テストクラス
 */
@SpringBootTest
public class CalcServiceTest {

    // テスト対象のクラスをインスタンス化
    private final CalcService calcService;
    // コンストラクタでCalcServiceを注入
    @Autowired
    public CalcServiceTest(CalcService calcService) {
        this.calcService = calcService; 
    }
    
    @Test
    @DisplayName("正常系: 2 + 3 が 5 になること")
    void testExecuteAddition() {
        // 実行 (Act) & 検証 (Assert)
        int result = calcService.execute(2, 3, "+");
        // 結果が期待値と等しいことを検証
        assertEquals(5, result);
    }

    @Test
    @DisplayName("正常系: 10 - 4 が 6 になること")
    void testExecuteSubtraction() {
        int result = calcService.execute(10, 4, "-");
        assertEquals(6, result);
    }

    @Test
    @DisplayName("異常系: ゼロ除算で IllegalArgumentException が発生すること")
    void testExecuteDivisionByZero() {
        // 例外が発生することを検証
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calcService.execute(10, 0, "/")
        );
        // 例外のメッセージが正しいか検証
        assertEquals("ゼロで割ることはできません", exception.getMessage());
    }

    @Test
    @DisplayName("異常系: 不正な演算子で IllegalArgumentException が発生すること")
    void testExecuteInvalidOperator() {
        assertThrows(
            IllegalArgumentException.class,
            () -> calcService.execute(10, 5, "^")
        );
    }
}
