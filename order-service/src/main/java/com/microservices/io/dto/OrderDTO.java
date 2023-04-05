package com.microservices.io.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private String orderNumber;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;
    private List<OrderItemDTO> orderItems;
    private boolean active;

}
