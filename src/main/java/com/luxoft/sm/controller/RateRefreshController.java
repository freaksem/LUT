package com.luxoft.sm.controller;

import com.luxoft.sm.domain.Currency;
import com.luxoft.sm.services.CurrencyService;
import com.luxoft.sm.services.RateService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Luxoft on 16.01.2017.
 */
@Controller
public class RateRefreshController {

    private CurrencyService currencyService;
    @Autowired
    public void setUserService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    private RateService rateService;
    @Autowired
    public void setUserService(RateService rateService) {
        this.rateService = rateService;
    }

    @RequestMapping("/rateRefresh")
    public @ResponseBody JSONObject rateRefreshController() {
        final List<Currency> currencyList = currencyService.getCurrencyList();
        ArrayList<HashMap<String, Float>> currRates = new ArrayList<>();
        for(int i=0; i<currencyList.size(); i++) {
            for(int j=0; j<currencyList.size(); j++) {
                if(i != j) {
                    currRates.add(rateService.calculateCurrencyRate(currencyList.get(i), currencyList.get(j)));
                }
            }
        }
        JSONObject jsonObject = new JSONObject();
        for(HashMap<String, Float> currencyRate: currRates) {
            jsonObject.putAll(currencyRate);
        }

        return jsonObject;
    }
}
