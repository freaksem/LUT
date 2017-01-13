package com.luxoft.sm.repository;

import com.luxoft.sm.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Luxoft on 12.01.2017.
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    @Modifying
    @Transactional
    @Query(value = "update Currency c set c.rate=:rate where c.id=:currencyId")
    void updateCurrencyRate(@Param("currencyId") Long currencyId, @Param("rate") Float rate);
}
