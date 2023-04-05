package com.microservices.io.mapper;

import com.microservices.io.dto.InventoryDTO;
import com.microservices.io.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    @Mapping(target="active", source="isActive")
    InventoryDTO toDto(Inventory inventory);
}
