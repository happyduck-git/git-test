package com.codestates.order.entity;

import com.codestates.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

// TODO V10
@Getter
@Setter
@Table(name = "ORDERS")
public class Order {
    @Id
    private long orderId;

    //Member class & Order class has 1 : N relationship & both are aggregate root=> Reference each other using ID!
    // 테이블 외래키 역할: memberId를 추가해서 참조하도록 한다.
   private AggregateReference<Member, Long> memberId;

   //CoffeeRef와 Order는 동일한 aggregate에 있음 => 객체 참조 사용!
    @MappedCollection(idColumn = "ORDER_ID") //Entity class 간에 연관 관계를 맺어주는 정보를 나타냄, "N에 해당하는 entity의 외래키"를 적어주면 됨!
    private Set<CoffeeRef> orderCoffees = new LinkedHashSet<>();

    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum OrderStatus {
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }

    }
}

