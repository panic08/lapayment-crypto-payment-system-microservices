package ru.panic.managementpaymentservice.template.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.panic.managementpaymentservice.template.dto.WebHookDto;
import ru.panic.managementpaymentservice.template.enums.CryptoCurrency;
import ru.panic.managementpaymentservice.template.enums.Status;
import ru.panic.managementpaymentservice.util.SHA256EncryptUtil;

@Component
public class WebHookUtil {
    public WebHookUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final RestTemplate restTemplate;
    public void handleWebHook(String URL, String o, double amount, CryptoCurrency currency, Long timestamp, String secretKey){
        WebHookDto webHookDto = new WebHookDto();
        webHookDto.setStatus(Status.COMPLETED);

        String sign = SHA256EncryptUtil.encrypt(o + secretKey + amount + secretKey + currency.toString() + secretKey + timestamp);

        webHookDto.setData(new WebHookDto.Data(o, amount, currency, timestamp, sign));

        restTemplate.postForEntity(URL, webHookDto, String.class);
    }
}
