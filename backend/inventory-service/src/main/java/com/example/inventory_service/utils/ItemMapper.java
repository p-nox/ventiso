package com.example.inventory_service.utils;

import com.example.inventory_service.entity.Item;
import com.example.inventory_service.response.ItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "categoryId", target = "category.id")
    Item toEntity(ItemResponse dto);
    @Mapping(source = "category.id", target = "categoryId")
    ItemResponse toDto(Item entity);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateItemFromDto(ItemResponse dto, @MappingTarget Item entity);

}
