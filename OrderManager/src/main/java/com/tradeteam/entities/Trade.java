package com.tradeteam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @AllArgsConstructor @NoArgsConstructor @ToString
public class Trade {

    private int tradeId;
    private LocalDateTime tradeTimestamp;
    private int numberTraded;
    private double pricePerShare;
    private Order connectedOrder;

    public String getFormattedTimestamp() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm:ss");
        return df.format(this.tradeTimestamp);
    }

    public String getFormattedPrice(String currencySymbol) {
        DecimalFormat df = new DecimalFormat(currencySymbol + "0.00");
        return df.format(this.pricePerShare);
    }

}
