package com.codestates.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@AllArgsConstructor
@Table(name = "ORDER_COFFEE")
public class CoffeeRef {
    private long coffeeId; //N:N을 풀어주는 상황에서 생기는 1:N에서는 AggregateReference로 감쌀필요가 없음!
    private int quantity;
}
