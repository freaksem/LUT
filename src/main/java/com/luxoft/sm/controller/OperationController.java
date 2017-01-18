package com.luxoft.sm.controller;

import com.luxoft.sm.domain.Currency;
import com.luxoft.sm.domain.Operation;
import com.luxoft.sm.repository.OperationRepository;
import com.luxoft.sm.services.CurrencyService;
import com.luxoft.sm.services.CurrentUser;
import com.luxoft.sm.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Luxoft on 12.01.2017.
 */
@Controller
public class OperationController {

    private OperationRepository operationRepository;
    @Autowired
    public void setOperationRepository(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    private OperationService operationService;
    @Autowired
    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }

    private CurrencyService currencyService;
    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping(value = "/operation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity operation(Authentication authentication,
                            @RequestParam String currencyToBuyParam,
                            @RequestParam String currencyToSellParam,
                            @RequestParam String summToBuyParam) {

        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();

        Currency currencyToBuy = currencyService.getCurrencyIdByCurrencyShortName(currencyToBuyParam);
        Currency currencyToSell = currencyService.getCurrencyIdByCurrencyShortName(currencyToSellParam);
        Long currencyToBuyId = currencyToBuy.getCurrencyId();
        Long currencyToSellId = currencyToSell.getCurrencyId();
        Float amountToBuy = Float.parseFloat(summToBuyParam);
        Float rate = currencyToBuy.getRate() / currencyToSell.getRate();
        Float amountToSell = amountToBuy * rate;
        Map<String, Float> currencySellBalance = operationService.getBalance(currentUser.getId(), currencyToSellId);
        Float sellBalance = 0F;
        for (Map.Entry<String, Float> entry : currencySellBalance.entrySet()) {
            sellBalance = entry.getValue();
        }
        if(sellBalance >= amountToSell) {
            Operation operation = new Operation(currentUser.getId(), new Date(), currencyToBuyId, amountToBuy, currencyToSellId, amountToSell, rate);
            operationRepository.save(operation);
            return ResponseEntity.ok().body("ok");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
