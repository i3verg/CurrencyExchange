package ua.frantsishko.currencyexchange.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "exchange")
public class ExchangeApplicationConfig {
    String url;
}
