package com.luxoft.sm.services;

import com.luxoft.sm.domain.Currency;

import java.util.List;

/**
 * Created by Luxoft on 13.01.2017.
 */
public interface CurrencyService {
    List<Currency> getCurrencyList();

    void updateCurrencyRate();
}
