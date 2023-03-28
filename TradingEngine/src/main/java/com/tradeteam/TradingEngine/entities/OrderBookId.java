package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class OrderBookId implements Serializable {

    @Column(name = "exchangeId")
    private String exchangeId;

    @Column(name = "companyAbbrev")
    private String companyAbbrev;

}
