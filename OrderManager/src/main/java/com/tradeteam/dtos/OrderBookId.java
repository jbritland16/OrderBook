package com.tradeteam.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @AllArgsConstructor @NoArgsConstructor @Setter
public class OrderBookId {

    String exchangeId;
    String companyAbbrev;

}
