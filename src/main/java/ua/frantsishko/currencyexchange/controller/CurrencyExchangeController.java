package ua.frantsishko.currencyexchange.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.frantsishko.currencyexchange.model.CurrencyExchangeValue;
import ua.frantsishko.currencyexchange.repository.CurrencyExchangeValueRepository;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "${app.rest.api.prefix}")
public class CurrencyExchangeController {
    private final Environment environment;
    private final CurrencyExchangeValueRepository exchangeValueRepository;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeValueRepository exchangeValueRepository) {
        this.environment = environment;
        this.exchangeValueRepository = exchangeValueRepository;
    }

    @GetMapping("/from-currency/{fromCurrency}/to-currency/{toCurrency}")
    public CurrencyExchangeValue retrieveCurrencyExchangeValue(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        log.info("getCurrencyExchangeRate, from:{}, to:{}", fromCurrency, toCurrency);
        CurrencyExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(fromCurrency, toCurrency);
        exchangeValue.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));
        return exchangeValue;
    }
}
