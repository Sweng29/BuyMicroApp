package com.microservices.io.mapper;

import com.microservices.io.dto.OrderItemDTO;
import com.microservices.io.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Named(value = "orderItem")
    @Mapping(target="active", source="isActive")
    OrderItemDTO toDto(OrderItem orderItem);
}
