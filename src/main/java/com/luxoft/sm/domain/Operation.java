package com.luxoft.sm.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Luxoft on 12.01.2017.
 */
@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long operationId;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "operationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operationDate;

    @Column(name = "currency_buy_id", nullable = false)
    private Long currencyBuyId;
    @Column(name = "currency_buy_summ", nullable = false)
    private Long currencyBuySumm;

    @Column(name = "currency_sell_id", nullable = false)
    private Long currencySellId;
    @Column(name = "currency_sell_summ", nullable = false)
    private Long currencySellSumm;

    @Column(name = "exchange_rate", nullable = false)
    private Long exchangeRate;

    public Long getOperationId() {
        return operationId;
    }
    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getOperationDate() {
        return operationDate;
    }
    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public Long getCurrencyBuyId() {
        return currencyBuyId;
    }
    public void setCurrencyBuyId(Long currencyBuyId) {
        this.currencyBuyId = currencyBuyId;
    }

    public Long getCurrencyBuySumm() {
        return currencyBuySumm;
    }
    public void setCurrencyBuySumm(Long currencyBuySumm) {
        this.currencyBuySumm = currencyBuySumm;
    }

    public Long getCurrencySellId() {
        return currencySellId;
    }
    public void setCurrencySellId(Long currencySellId) {
        this.currencySellId = currencySellId;
    }

    public Long getCurrencySellSumm() {
        return currencySellSumm;
    }
    public void setCurrencySellSumm(Long currencySellSumm) {
        this.currencySellSumm = currencySellSumm;
    }

    public Long getExchangeRate() {
        return exchangeRate;
    }
    public void setExchangeRate(Long exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @PrePersist
    protected void onCreate() {
        this.operationDate = new Date();
    }

    public Operation(){}

    public Operation(Long userId, Date operationDate, Long currencyBuyId, Long currencyBuySumm,
                                  Long currencySellId, Long currencySellSumm, Long exchangeRate) {
        this.userId = userId;
        this.operationDate = operationDate;
        this.currencyBuyId = currencyBuyId;

        this.currencyBuySumm = currencyBuySumm;
        this.currencySellId = currencySellId;
        this.currencySellSumm = currencySellSumm;
        this.exchangeRate = exchangeRate;
    }

}
