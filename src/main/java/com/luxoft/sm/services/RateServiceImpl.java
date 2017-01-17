package com.luxoft.sm.services;

import com.luxoft.sm.domain.Currency;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by Luxoft on 13.01.2017.
 */
@Service
public class RateServiceImpl implements RateService {

    @Override
    public HashMap<String, Float> calculateCurrencyRate(Currency baseCurrency, Currency quotation) {
        HashMap<String, Float> currenciesRate = new HashMap<>();
        float rate = quotation.getRate()/baseCurrency.getRate();

        currenciesRate.put(baseCurrency.getCurrencyShortName() + "/" + quotation.getCurrencyShortName(), rate);
        return  currenciesRate;
    }

}
