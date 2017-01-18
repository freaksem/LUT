package com.luxoft.sm.services;

import com.luxoft.sm.domain.Currency;
import com.luxoft.sm.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created by Luxoft on 13.01.2017.
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;

    @Autowired
    public void setCurrencyRepository(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> getCurrencyList() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getCurrencyIdByCurrencyShortName(String currencyShortName) {
        return currencyRepository.findOneByCurrencyShortName(currencyShortName);
    }

    @Override
    public Currency getCurrencyById(Long currencyId) {
        return currencyRepository.findOne(currencyId);
    }

    @Override
    @Scheduled(fixedRate = 10000)
    public void updateCurrencyRate() {
        List<Currency> currencyList = currencyRepository.findAll();
        for(Currency currency: currencyList) {
            float min = 1.0f;
            float max = 99.0f;

            Random rand = new Random();

            float newCurrency = rand.nextFloat() * (max - min) + min;
            currencyRepository.updateCurrencyRate(currency.getCurrencyId(), newCurrency);
            System.out.println("Currency " + currency.getCurrencyFullName() + " is updated: " + currency.getRate());
        }
    }


}
