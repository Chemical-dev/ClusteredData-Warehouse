package com.clustereddatawarehouse.model;

import com.clustereddatawarehouse.annotations.Unique;
import lombok.*;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name="deal")
@NoArgsConstructor()
@AllArgsConstructor()
public class Deal extends BaseDeal{
    @Unique
    @Column(name = "deal_id", length = 20, unique = true, nullable = false)
    private String dealUniqueId;

    @Column(name="from_currency_isoCode", nullable=false)
    private String fromCurrencyIsoCode;

    @Column(name="to_currency_isoCode", nullable=false)
    private String toCurrencyIsoCode;

    @Column(name="deal_timestamp", nullable=false)
    private LocalDateTime dealTimestamp;

    @Column(name="deal_amount", nullable=false)
    private BigDecimal dealAmount;
}
