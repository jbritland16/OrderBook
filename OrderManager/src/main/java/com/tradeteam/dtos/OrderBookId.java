package com.tradeteam.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @AllArgsConstructor @NoArgsConstructor @Setter
public class OrderBookId {

    String exchangeId;
    String companyAbbrev;

    public static OrderBookId of(String orderBookIdString) {
        int separateIndex = orderBookIdString.indexOf(":");
        if (separateIndex < 0) {
            return new OrderBookId("error", "error");
        }
        else {
            return new OrderBookId(orderBookIdString.substring(0, separateIndex),
                    orderBookIdString.substring(separateIndex + 1));
        }
    }

}
