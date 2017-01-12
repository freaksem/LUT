package com.luxoft.sm.repository;

import com.luxoft.sm.domain.Operation;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Luxoft on 12.01.2017.
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {
    Optional<Operation> findOneByOperationId(Long operationId);
    Optional<List<Operation>> findAllOperationsByUserId(Long userId);
    Optional<List<Operation>> findFirst20OperationsByUserId(Long userId, Sort sort);
    Optional<List<Operation>> findAllOperationsByUserIdAndCurrencyBuyId(Long userId, Long currencyId);
}
