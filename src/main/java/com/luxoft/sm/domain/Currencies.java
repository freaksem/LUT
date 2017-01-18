package com.luxoft.sm.domain;

/**
 * Created by Luxoft on 13.01.2017.
 */
public enum Currencies {
    RU("RUR", "Рубль", 1F),
    US("USD", "Доллар", 65F),
    EU("EUR", "Евро", 75F);

    private final String shortName;
    private final String fullName;
    private final Float rate;

    Currencies(String shortName, String fullName, Float rate) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.rate = rate;
    }
    public String getShortName() {
        return this.shortName;
    }
    public String getFullName() {
        return this.fullName;
    }
    public Float getRate() {
        return this.rate;
    }
}
