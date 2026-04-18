package com.example.java_actions_app.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.java_actions_app.service.CalcService;

/**
 * 計算コントローラークラス
 */
@Controller
public class CalcController {
    /**
     * 計算サービスクラスのインスタンス
     */
    private final CalcService calcService;
    /**
     * コンストラクタでCalcServiceを注入
     * @param calcService 計算サービスクラスのインスタンス
     */
    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }

    /**
     * 初期画面の表示
     */
    @GetMapping("/calc")
    public String showForm() {
        return "calc"; // calc.htmlを返す
    }

    /**
     * 計算処理の実行
     */
    @PostMapping("/calc")
    public String calculate(
        @RequestParam int v1,@RequestParam int v2,@RequestParam String op,Model model) {
        
        try {
            // Serviceを呼び出して計算
            int result = calcService.execute(v1, v2, op);
            model.addAttribute("result", result); // 画面に結果を渡す
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage()); // 画面にエラーを渡す
        }
        
        // 入力値を画面に戻す
        model.addAttribute("v1", v1);
        model.addAttribute("v2", v2);
        model.addAttribute("op", op);
        
        return "calc"; // 再度 calc.html を表示
    }
}
