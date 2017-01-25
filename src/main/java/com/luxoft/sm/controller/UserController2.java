package com.luxoft.sm.controller;

import com.luxoft.sm.domain.Currency;
import com.luxoft.sm.domain.Operation;
import com.luxoft.sm.services.CurrencyService;
import com.luxoft.sm.services.CurrentUser;
import com.luxoft.sm.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sm on 10.01.2017.
 */
@RestController
public class UserController2 {

    private CurrencyService currencyService;
    @Autowired
    public void setUserService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    private OperationService operationService;
    @Autowired
    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }

    @RequestMapping("/api/user")
    public Map<String, Object> userController(Authentication authentication) {
        Map<String, Object> model = new HashMap<>();
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();

        if(currentUser != null) {
            String loggedUser = currentUser.getUsername();

            //last 20 operations sorted by date
            if(operationService.findFirst20OperationsByUserId(currentUser.getId()).isPresent()) {
                List<Operation> userOperations = operationService.findFirst20OperationsByUserId(currentUser.getId()).get();
                for(Operation operationItem: userOperations) {
                    Long currencyBuyId = operationItem.getCurrencyBuyId();
                    Long currencySellId = operationItem.getCurrencySellId();
                    Currency buyCurrency = currencyService.getCurrencyById(currencyBuyId);
                    Currency sellCurrency = currencyService.getCurrencyById(currencySellId);
                    operationItem.setCurrencyBuyFullName(buyCurrency.getCurrencyFullName());
                    operationItem.setCurrencySellFullName(sellCurrency.getCurrencyFullName());
                }
                model.put("userOperations", userOperations);
            }

            //all currencies balance
            List<Currency> currencyList = currencyService.getCurrencyList();
            ArrayList<Map<String, Float>> currencyBalances = new ArrayList<>();
            for(Currency currency: currencyList) {
                currencyBalances.add(operationService.getBalance(currentUser.getId(), currency.getCurrencyId()));
            }

            model.put("loggedUserName", loggedUser);
            model.put("currencyBalances", currencyBalances);
            model.put("currencyList", currencyList);

        }
        return model;
    }

}