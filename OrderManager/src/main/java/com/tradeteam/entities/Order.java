package com.tradeteam.entities;

import jakarta.persistence.*;
import lombok.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor @ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    @NonNull private LocalDateTime orderTimestamp;
    @NonNull private int numberOrdered;
    @NonNull private int numberFulfilled = 0;
    @NonNull private double price;
    @NonNull private boolean orderActive = true;


    public enum OrderType {
        BUY,
        SELL
    }
    @NonNull private OrderType orderType;

    @NonNull private int userId;

    @NonNull private String companyAbbrev;

    @NonNull private String exchangeId;

    public Order(int numberOrdered, double price, String orderType, int userId, String companyAbbrev, String exchangeId) {
        this.orderTimestamp = LocalDateTime.now();
        this.numberOrdered = numberOrdered;
        this.price = price;
        this.orderType = OrderType.valueOf(orderType);
        this.userId = userId;
        this.companyAbbrev = companyAbbrev;
        this.exchangeId = exchangeId;
    }

    public String getStatus(){
        if(this.isOrderActive() == true){
            return "Active";
        } else if (numberFulfilled == numberOrdered){
            return "Complete";
        }
        else {
            return "Cancelled";
        }
    }

    public String getFormattedTimestamp() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm:ss");
        return df.format(this.orderTimestamp);
    }

    public String getFormattedPrice(String currencySymbol) {
        DecimalFormat df = new DecimalFormat(currencySymbol + "0.00");
        return df.format(this.price);
    }

}
