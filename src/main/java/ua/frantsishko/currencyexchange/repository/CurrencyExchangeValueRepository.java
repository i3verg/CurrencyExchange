package ua.frantsishko.currencyexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.frantsishko.currencyexchange.model.CurrencyExchangeValue;

public interface CurrencyExchangeValueRepository extends JpaRepository<CurrencyExchangeValue, Long> {
    CurrencyExchangeValue findByFromAndTo(String from, String to);
}
