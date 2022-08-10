package ua.frantsishko.currencyexchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.frantsishko.currencyexchange.model.CurrencyExchangeValue;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.LocalDate;

@Configuration
@EnableConfigurationProperties(ExchangeApplicationConfig.class)
public class ApplicationConfiguration {
    private final CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

    @Bean
    public Cache<LocalDate, CurrencyExchangeValue> currencyRateCache(@Value("${app.cache.size}") int cacheSize) {
        return cacheManager
                .createCache("CurrencyExchange-Cache", CacheConfigurationBuilder.newCacheConfigurationBuilder(LocalDate.class,
                                CurrencyExchangeValue.class,
                                ResourcePoolsBuilder.heap(cacheSize))
                        .build()
                );
    }
}