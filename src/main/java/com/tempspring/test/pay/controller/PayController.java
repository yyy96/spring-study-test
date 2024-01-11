package com.tempspring.test.pay.controller;

import com.tempspring.test.pay.service.KakaoPayService;
import com.tempspring.test.pay.service.NaverPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
@Tag(name = "결제 관리 API", description = "카카오,네이버 페이 등을 위한 API")
//@Import(PayLogAspect.class)
public class PayController {

    @Autowired
    private KakaoPayService kakaoPayService; //JDK

    @Autowired
    private NaverPayService naverPayService; //CGLIB

    @GetMapping("/kakao")
    public String kakaoPay() {
        kakaoPayService.pay();
        return "KakaoPay";
    }

    @Operation(summary = "네이버 페이 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "네이버페이 결제 성공"),
            @ApiResponse(responseCode = "500", description = "네이버페이 결제 실패")
    })
    @GetMapping("/naver")
    public void naverPay(
            @Parameter(name = "payCode", description = "결제코드", example = "01xcd2", required = false) Integer payCode
    ) {
        naverPayService.pay();
    }




    /**
     * Self-invocation Test <br>
     * 1. internal이 직접 외부에서 호출당하는 경우 (트랜잭션 확인) <br>
     * 2. internal이 external 통해서 객체 내부에서 호출당하는 경우 (트랜잭션 확인) <br>
     */
    @GetMapping("/kakao/in")
    public void kakaoPayInternalCall() {
        //1. 곧장 트랜잭션이 있는 메서드 호출 시
        kakaoPayService.inTransactionPay();
        //결론: 내부 호출이 아니므로 트랜잭션이 걸린다.
    }

    @GetMapping("/kakao/ex")
    public void kakaoPayExtoInCall1() {
        //2. 트랜잭션이 걸린(o) 메서드를 통해 -> 내부 메서드(트랜잭션 x)를 호출시
        //2. 트랜잭션이 걸린(o) 메서드를 통해 -> 내부 메서드(트랜잭션 o)를 호출시
        kakaoPayService.exTransactionPay();
        // 결론: 두 경우 모두, 둘다 트랜잭션이 걸린다.
    }

    @GetMapping("/kakao/ex2")
    public void kakaoPayExtoInCall2() {
        //2. 트랜잭션이 없는(x) 메서드를 통해 -> 내부 메서드(트랜잭션 o)를 호출시
        kakaoPayService.exPay();
        // 결론: 둘다 트랜잭션이 걸리지 않는다.
    }
}
