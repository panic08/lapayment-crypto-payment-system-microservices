package ru.panic.paymentservice.template.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.panic.paymentservice.template.dto.PaymentRequest;
import ru.panic.paymentservice.template.dto.PaymentResponse;
import ru.panic.paymentservice.template.service.impl.PaymentServiceImpl;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class PaymentController {
    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }
    private final PaymentServiceImpl paymentService;
    @PostMapping("/payByBtc")
    private PaymentResponse handlePaymentByBitcoin(@RequestParam PaymentRequest paymentRequest){
        return paymentService.handlePaymentByBitcoin(paymentRequest);
    }
    @PostMapping("/payByEth")
    private PaymentResponse handlePaymentByEthereum(@RequestParam PaymentRequest paymentRequest){
        return paymentService.handlePaymentByEthereum(paymentRequest);
    }
    @PostMapping("/payByLtc")
    private PaymentResponse handlePaymentByLitecoin(@RequestParam PaymentRequest paymentRequest){
        return paymentService.handlePaymentByLitecoin(paymentRequest);
    }
    @PostMapping("/payByTrx")
    private PaymentResponse handlePaymentByTron(@RequestParam PaymentRequest paymentRequest){
        return paymentService.handlePaymentByTron(paymentRequest);
    }
    @PostMapping("/payByTon")
    private PaymentResponse handlePaymentByTon(@RequestParam PaymentRequest paymentRequest){
        return paymentService.handlePaymentByTon(paymentRequest);
    }
    @PostMapping("/payByMatic")
    private PaymentResponse handlePaymentByMatic(@RequestParam PaymentRequest paymentRequest){
        return paymentService.handlePaymentByMatic(paymentRequest);
    }
    @PostMapping("/payByXrp")
    private PaymentResponse handlePaymentByRipple(@RequestParam PaymentRequest paymentRequest){
        return paymentService.handlePaymentByRipple(paymentRequest);
    }
    @PostMapping("/payByTetherERC20")
    private PaymentResponse handlePaymentByTetherERC20(@RequestParam PaymentRequest paymentRequest){
        return paymentService.handlePaymentByTetherERC20(paymentRequest);
    }
}
