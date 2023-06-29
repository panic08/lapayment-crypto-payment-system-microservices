package ru.panic.withdrawalservice.template.controller;

import org.springframework.web.bind.annotation.*;
import ru.panic.withdrawalservice.template.dto.CreateWithdrawalRequest;
import ru.panic.withdrawalservice.template.dto.CreateWithdrawalResponse;
import ru.panic.withdrawalservice.template.service.impl.WithdrawalServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/withdrawal")
public class WithdrawalController {
    public WithdrawalController(WithdrawalServiceImpl withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    private final WithdrawalServiceImpl withdrawalService;
    @PostMapping("/create")
    private CreateWithdrawalResponse createWithdrawal(@RequestBody CreateWithdrawalRequest createWithdrawalRequest){
        return withdrawalService.createWithdrawal(createWithdrawalRequest);
    }
}
