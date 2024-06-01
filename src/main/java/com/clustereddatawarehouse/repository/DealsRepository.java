package com.clustereddatawarehouse.repository;

import com.clustereddatawarehouse.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface DealsRepository extends JpaRepository<Deal, Long> {
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Deal d " +
            "WHERE d.dealUniqueId = :dealUniqueId " +
            "OR d.dealAmount = :dealAmount " +
            "OR d.fromCurrencyIsoCode = :fromCurrencyIsoCode " +
            "OR d.toCurrencyIsoCode = :toCurrencyIsoCode")
    Boolean existsByDealUniqueIdOrDealAmountOrFromCurrencyIsoCodeOrToCurrencyIsoCode(
            @Param("dealUniqueId") String dealUniqueId,
            @Param("dealAmount") BigDecimal dealAmount,
            @Param("fromCurrencyIsoCode") String fromCurrencyIsoCode,
            @Param("toCurrencyIsoCode") String toCurrencyIsoCode);

    Boolean existsByDealUniqueId(String dealUniqueId);
}
