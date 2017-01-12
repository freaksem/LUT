package com.luxoft.sm.repository;

import com.luxoft.sm.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Luxoft on 12.01.2017.
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
