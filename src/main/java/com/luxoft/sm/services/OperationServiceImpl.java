package com.luxoft.sm.services;

import com.luxoft.sm.domain.Currency;
import com.luxoft.sm.domain.Operation;
import com.luxoft.sm.repository.CurrencyRepository;
import com.luxoft.sm.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Luxoft on 12.01.2017.
 */
@Service
public class OperationServiceImpl implements OperationService {

    private OperationRepository operationRepository;
    private CurrencyRepository currencyRepository;

    @Autowired
    public void setOperationRepository(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Autowired
    public void setCurrencyRepository(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Optional<List<Operation>> findAllOperationsByUserId(Long userId) {
        return operationRepository.findAllOperationsByUserId(userId);
    }

    @Override
    public Optional<List<Operation>> findFirst20OperationsByUserId(Long userId) {
        return operationRepository.findFirst20OperationsByUserId(userId, new Sort(Sort.Direction.DESC,"operationDate"));
    }

    @Override
    public Map<String, Long> getBalance(Long userId, Long currencyId) {
        Long totalBalance = 0L;
        HashMap<String, Long> currencyBalance = new HashMap<>();
        if(operationRepository.findAllOperationsByUserId(userId).isPresent()) {
            List<Operation> operations = operationRepository.findAllOperationsByUserId(userId).get();
            for(Operation operation: operations) {
                if(Objects.equals(operation.getCurrencyBuyId(), currencyId)) {
                    totalBalance = totalBalance + operation.getCurrencyBuySumm();
                }
                if(Objects.equals(operation.getCurrencySellId(), currencyId)) {
                    totalBalance = totalBalance - operation.getCurrencySellSumm();
                }
                Currency currency = currencyRepository.findOne(currencyId);
                currencyBalance.put(currency.getCurrencyFullName(), totalBalance);
            }
        }
        return currencyBalance;
    }


}
