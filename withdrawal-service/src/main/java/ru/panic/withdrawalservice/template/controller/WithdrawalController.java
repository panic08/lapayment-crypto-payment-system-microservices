package ru.panic.withdrawalservice.template.controller;

import org.springframework.web.bind.annotation.*;
import ru.panic.withdrawalservice.template.dto.CreateWithdrawalRequest;
import ru.panic.withdrawalservice.template.dto.CreateWithdrawalResponse;
import ru.panic.withdrawalservice.template.entity.Withdrawal;
import ru.panic.withdrawalservice.template.service.impl.WithdrawalServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/withdrawal")
public class WithdrawalController {
    public WithdrawalController(WithdrawalServiceImpl withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    private final WithdrawalServiceImpl withdrawalService;
    @GetMapping("/readByUsername")
    private List<Withdrawal> readAllWithdrawalByUsername(@RequestHeader String jwtToken){
        return withdrawalService.readAllWithdrawalByUsername(jwtToken);
    }
    @PostMapping("/create")
    private CreateWithdrawalResponse createWithdrawal(
            @RequestHeader String jwtToken,
            @RequestBody CreateWithdrawalRequest createWithdrawalRequest){

        return withdrawalService.createWithdrawal(jwtToken, createWithdrawalRequest);
    }
    @DeleteMapping("/delete")
    private void deleteWithdrawal(
            @RequestHeader String jwtToken,
            @RequestBody Withdrawal withdrawal
    ){
        withdrawalService.deleteWithdrawal(jwtToken, withdrawal);
    }
}
