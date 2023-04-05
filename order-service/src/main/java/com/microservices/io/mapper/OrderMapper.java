package com.microservices.io.mapper;

import com.microservices.io.dto.OrderDTO;
import com.microservices.io.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(target="active", source="isActive")
    @Mapping(target = "orderItems", source = "orderItems", conditionQualifiedByName = "orderItem")
    OrderDTO toDto(Order order);

}
