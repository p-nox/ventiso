package com.example.inventory_service.service;


import com.example.inventory_service.enums.ItemCondition;
import com.example.inventory_service.enums.ItemStatus;
import com.example.inventory_service.response.ItemResponse;

import java.util.List;

public interface InventoryService {

    ItemResponse getItemById(Long itemId);
    List<ItemResponse> getAllUserItems(Long userId);
    List<ItemResponse> getItemsByCategory(Long categoryId);

    void createItem(ItemResponse itemResponse);
    void deleteItem(Long itemId);
    ItemResponse updateItem(Long itemId, ItemResponse updatedItem);
    void updateStatus(Long itemId, ItemStatus status);

    List<ItemResponse> searchItems(
            String title,
            Long categoryId,
            ItemCondition condition,
            ItemStatus status,
            Long userId,
            Float minPrice,
            Float maxPrice);
}
