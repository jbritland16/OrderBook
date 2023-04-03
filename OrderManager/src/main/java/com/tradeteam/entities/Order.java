package com.tradeteam.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="orders")
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

    public Order(int numberOrdered, double price, String orderType, int userId, String companyAbbrev, int exchangeId) {
        this.numberOrdered = numberOrdered;
        this.price = price;
        this.orderType = OrderType.valueOf(orderType);
        this.userId = userId;
        this.companyAbbrev = companyAbbrev;
        this.exchangeId = String.valueOf(exchangeId);

    }

    @PrePersist
    public void setDefaultValues() {
        if (orderTimestamp == null) {
            orderTimestamp = LocalDateTime.now();
        }
    }

    public String getStatus(){
        if(this.isOrderActive() == true){
            return "Active";
        } else {
            return "Inactive";
        }
    }
}
