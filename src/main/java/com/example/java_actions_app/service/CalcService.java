package com.example.java_actions_app.service;
import org.springframework.stereotype.Service;
/**
 * 計算サービスクラス
 */
@Service
public class CalcService {
    /**
     * 計算を実行するメソッド
     * 
     * @param value1   最初の値
     * @param value2   2番目の値
     * @param operator 演算子（+、-、*、/）
     * @return 計算結果
     * @throws IllegalArgumentException 不正な演算子やゼロでの割りが発生した場合
     */
    public int execute(int value1, int value2, String operator) {
        switch (operator) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                if (value2 == 0) {
                    throw new IllegalArgumentException("ゼロで割ることはできません");
                }
                return value1 / value2;
            default:
                throw new IllegalArgumentException("不正な演算子: " + operator);
        }
    }
}
