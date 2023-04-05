package com.tradeteam.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
}
