package com.tradeteam.TradingEngine.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter @AllArgsConstructor @NoArgsConstructor
public class OrderBookId implements Serializable {

    Exchange exchange;
    String companyAbbrev;

}
