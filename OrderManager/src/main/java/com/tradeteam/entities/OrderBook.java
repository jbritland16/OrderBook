package com.tradeteam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class OrderBook {

    private String companyAbbrev;
    private String exchangeId;
    private String companyName;
    private List<Order> orders;

}
