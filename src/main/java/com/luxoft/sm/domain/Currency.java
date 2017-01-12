package com.luxoft.sm.domain;

import javax.persistence.*;

/**
 * Created by Luxoft on 12.01.2017.
 */
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long currencyId;

    @Column(name = "currencyShortName", nullable = false, unique = true)
    private String currencyShortName;

    @Column(name = "currencyFullName", nullable = false, unique = true)
    private String currencyFullName;

    public Currency() {}

    public Currency(String currencyShortName, String currencyFullName) {
        this.currencyShortName = currencyShortName;
        this.currencyFullName = currencyFullName;
    }

    public Long getCurrencyId() {
        return currencyId;
    }
    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyShortName() {
        return currencyShortName;
    }
    public void setCurrencyShortName(String currencyShortName) {
        this.currencyShortName = currencyShortName;
    }

    public String getCurrencyFullName() {
        return currencyFullName;
    }
    public void setCurrencyFullName(String currencyFullName) {
        this.currencyFullName = currencyFullName;
    }
}
