package ru.panic.managementpaymentservice.template.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.panic.managementpaymentservice.template.enums.CryptoCurrency;
import ru.panic.managementpaymentservice.template.enums.Status;

@Setter
public class WebHookDto {
    private Status status;
    private Data data;
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        private String o;
        private Double amount;
        private CryptoCurrency currency;
        private Long timestamp;
        private String sign;
    }
}
