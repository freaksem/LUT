package com.luxoft.sm.services;

import com.luxoft.sm.domain.Currency;

import java.util.HashMap;

/**
 * Created by Luxoft on 13.01.2017.
 */
public interface RateService {
    HashMap<String, Float> calculateCurrencyRate(Currency baseCurrency, Currency quotation);
}
