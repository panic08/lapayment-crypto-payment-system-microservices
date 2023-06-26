package ru.panic.paymentservice.template.bunny;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class NetworkBunny {
    @Value("${ru.panic.networks.btc}")
    private String btcWallet;
    @Value("${ru.panic.networks.eth}")
    private String ethWallet;
    @Value("${ru.panic.networks.ltc}")
    private String ltcWallet;
    @Value("${ru.panic.networks.sol}")
    private String tetherERC20Wallet;
    @Value("${ru.panic.networks.trx}")
    private String trxWallet;
    @Value("${ru.panic.networks.matic}")
    private String maticWallet;
    @Value("${ru.panic.networks.doge}")
    private String xrpWallet;
    @Value("${ru.panic.networks.ton}")
    private String tonWallet;
    @Value("${ru.panic.networks.api.eth}")
    private String ethApiToken;
}
