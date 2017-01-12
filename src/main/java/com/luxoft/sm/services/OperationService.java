package com.luxoft.sm.services;

import com.luxoft.sm.domain.Operation;

import java.util.List;
import java.util.Optional;

/**
 * Created by Luxoft on 12.01.2017.
 */
public interface OperationService {
    Optional<List<Operation>> findAllOperationsByUserId(Long userId);
    Optional<List<Operation>> findFirst20OperationsByUserId(Long userId);
    Long getBalance(Long userId, Long currencyId);
}
