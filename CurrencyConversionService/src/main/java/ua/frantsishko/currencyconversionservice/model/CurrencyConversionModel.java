package ua.frantsishko.currencyconversionservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class CurrencyConversionModel {
    private String id;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal conversionMultiple;
    private BigDecimal amount;
    private BigDecimal totalAmount;
    private int port;


    public CurrencyConversionModel(String fromCurrency, String toCurrency, BigDecimal conversionMultiple, BigDecimal amount, BigDecimal totalAmount, int port) {
        super();
        this.id = UUID.randomUUID().toString();
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.conversionMultiple = conversionMultiple;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.port = port;
    }
}
