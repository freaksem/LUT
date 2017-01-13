package com.luxoft.sm.controller;

import com.luxoft.sm.domain.Currency;
import com.luxoft.sm.domain.Operation;
import com.luxoft.sm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sm on 10.01.2017.
 */
@Controller
public class UserController {

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

    private OperationService operationService;
    @Autowired
    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }

    @RequestMapping("/user")
    public String userController(Map<String, Object> model, Authentication authentication) {

        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();

        if(currentUser != null) {
            String loggedUser = currentUser.getUsername();

            //last 20 operations sorted by date
            if(operationService.findFirst20OperationsByUserId(currentUser.getId()).isPresent()) {
                List<Operation> userOperations = operationService.findFirst20OperationsByUserId(currentUser.getId()).get();
                model.put("userOperations", userOperations);
            }

            //all currencies balance
            List<Currency> currencyList = currencyService.getCurrencyList();
            ArrayList<Map<String, Long>> currencyBalances = new ArrayList<>();
            for(Currency currency: currencyList) {
                currencyBalances.add(operationService.getBalance(currentUser.getId(), currency.getCurrencyId()));
            }



            model.put("loggedUserName", loggedUser);
            model.put("currencyBalances", currencyBalances);
            model.put("currencyList", currencyList);

            HashMap<String, Float> ruUsRate = rateService.calculateCurrencyRate(currencyList.get(0), currencyList.get(1));
            HashMap<String, Float> ruEuRate = rateService.calculateCurrencyRate(currencyList.get(0), currencyList.get(2));
            HashMap<String, Float> usEuRate = rateService.calculateCurrencyRate(currencyList.get(1), currencyList.get(2));
            model.put("ruUsRate", ruUsRate);
            model.put("ruEuRate", ruEuRate);
            model.put("usEuRate", usEuRate);
        }
        return "user";
    }

}