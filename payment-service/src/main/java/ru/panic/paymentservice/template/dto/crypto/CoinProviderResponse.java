package ru.panic.paymentservice.template.dto.crypto;

import lombok.Getter;

@Getter
public class CoinProviderResponse {
    private CoinDto bitcoin;
    private CoinDto ripple;
    private CoinDto ethereum;
    private CoinDto litecoin;
    private CoinDto tron;
    private CoinDto theOpenNetwork;
    private CoinDto maticNetwork;
    private CoinDto tether;

    @Getter
    public static class CoinDto {
        private double usd;
        private double eur;
        private double rub;
        private double pln;
    }
}