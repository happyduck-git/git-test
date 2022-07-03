package com.codestates.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;


@AllArgsConstructor
public class OrderCoffeeResponseDto {
   private long coffeeId;
   private String korName;
   private String engName;
   private int price;
   private int quantity;
}
