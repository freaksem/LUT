package com.luxoft.sm.bootstrap;

import com.luxoft.sm.domain.Currencies;
import com.luxoft.sm.domain.Currency;
import com.luxoft.sm.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Luxoft on 13.01.2017.
 */
@Component
public class CurrencyLoader implements ApplicationListener<ContextRefreshedEvent> {
    private CurrencyRepository currencyRepository;

    @Autowired
    public void setCurrencyRepository(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for(Currencies currencyItem: Currencies.values()) {
            Currency currency = new Currency(currencyItem.getShortName(), currencyItem.getFullName(), currencyItem.getRate());
            currencyRepository.save(currency);
        }
    }
}
