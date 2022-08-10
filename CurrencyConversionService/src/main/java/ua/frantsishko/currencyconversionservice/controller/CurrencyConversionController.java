package ua.frantsishko.currencyconversionservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ua.frantsishko.currencyconversionservice.model.CurrencyConversionModel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "${app.rest.api.prefix}")
public class CurrencyConversionController {
    private final String exchangeUrl;

    public CurrencyConversionController(Environment environment) {
        this.exchangeUrl = environment.getProperty("exchange.url");
    }

    @GetMapping("/from-currency/{fromCurrency}/to-currency/{toCurrency}/amount/{amount}")
    public CurrencyConversionModel convertCurrency(@PathVariable String fromCurrency,
                                                   @PathVariable String toCurrency,
                                                   @PathVariable BigDecimal amount) {
        var uriMap = new HashMap<String, String>();
        uriMap.put("fromCurrency", fromCurrency);
        uriMap.put("toCurrency", toCurrency);



        ResponseEntity<CurrencyConversionModel> responseEntity = new RestTemplate().getForEntity(
                exchangeUrl,
                CurrencyConversionModel.class,
                uriMap);

        var conversionModelResponse = responseEntity.getBody();
        var multiplier = Objects.requireNonNull(conversionModelResponse).getConversionMultiple();
        var totalAmount = amount.multiply(multiplier);
        var port = conversionModelResponse.getPort();

        return new CurrencyConversionModel(fromCurrency, toCurrency, multiplier, amount, totalAmount, port);
    }
}
