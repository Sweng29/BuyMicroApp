package com.microservices.io.dto.request;

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
public class OrderRequest {

    private List<OrderItemRequest> orderItems;
    @Getter
    @Setter
    public static class OrderItemRequest{
        private Integer quantity;
        private String skuCode;
        private BigDecimal itemUnitPrice;
        private BigDecimal discountPerItem;
    }

}
