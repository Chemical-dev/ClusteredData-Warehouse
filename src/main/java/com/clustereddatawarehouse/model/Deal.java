package com.clustereddatawarehouse.model;

import com.clustereddatawarehouse.annotations.PositiveAmount;
import com.clustereddatawarehouse.annotations.Unique;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;

import javax.validation.constraints.PositiveOrZero;
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
@EqualsAndHashCode(of={"dealUniqueId"}, callSuper=false)
//@Where(clause="deleted=false")
//@SQLDelete(sql="UPDATE deal SET deleted=true WHERE id=?")
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

    @PositiveOrZero
    @Column(name="deal_amount", nullable=false)
    private BigDecimal dealAmount;
}
