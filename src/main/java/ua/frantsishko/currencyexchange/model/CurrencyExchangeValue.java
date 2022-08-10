package ua.frantsishko.currencyexchange.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CurrencyExchangeValue {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public long id;

    @Column(name = "currency_from")
    public String from;

    @Column(name = "currency_to")
    public String to;

    private BigDecimal conversionMultiple;
    private int port;

    public CurrencyExchangeValue() {
    }

    public CurrencyExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple){
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
