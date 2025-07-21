package com.example.inventory_service.service;

import com.example.inventory_service.enums.ItemCondition;
import com.example.inventory_service.enums.ItemStatus;
import com.example.inventory_service.response.ItemResponse;
import com.example.inventory_service.entity.Item;
import com.example.inventory_service.exception.ResourceNotFoundException;
import com.example.inventory_service.repository.InventoryRepository;
import com.example.inventory_service.utils.ItemMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService{


    private final InventoryRepository inventoryRepository;
    private final ItemMapper itemMapper;

    @Override
    public ItemResponse getItemById(Long itemId) {
        return itemMapper.toDto(findItemById(itemId));
    }

    @Override
    public List<ItemResponse> getAllUserItems(Long userId) {

      List<Item> items = inventoryRepository.findAllByUserId(userId);

      return convertToDtoList(items);

    }

    @Override
    public List<ItemResponse> getItemsByCategory(Long categoryId) {

        List<Item> items = inventoryRepository.findAllByCategoryId(categoryId);
        return convertToDtoList(items);

    }


    @Override
    public void createItem(ItemResponse itemResponse) {
        inventoryRepository.save(itemMapper.toEntity(itemResponse));

    }

    @Override
    public void deleteItem(Long itemId) {

        inventoryRepository.delete(findItemById(itemId));

    }

    @Override
    public ItemResponse updateItem(Long itemId, ItemResponse updatedItem) {

        Item existingItem = findItemById(itemId);
        itemMapper.updateItemFromDto(updatedItem, existingItem);

        return itemMapper.toDto(inventoryRepository.save(existingItem));

    }

    @Override
    public void updateStatus(Long itemId, ItemStatus status){
        Item item = findItemById(itemId);
        item.setStatus(status);
        inventoryRepository.save(item);
    }

    @Override
    public List<ItemResponse> searchItems(
            String title,
            Long categoryId,
            ItemCondition condition,
            ItemStatus status,
            Long userId,
            Float minPrice,
            Float maxPrice) {

       List<Item> items = inventoryRepository.searchItems(title, categoryId, condition, status, userId, minPrice, maxPrice);
       return  convertToDtoList(items);
    }

    private Item findItemById(Long itemId){
        log.info("Fetching item with id {}", itemId);

        Item updatedItem = inventoryRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item with id " + itemId + " doesn't exist!", itemId));

        log.info("Updating item id {} with data: title='{}', description='{}', price={}, condition='{}', status='{}', userId={}, categoryId={}",
                itemId,
                updatedItem.getTitle(),
                updatedItem.getDescription(),
                updatedItem.getPrice(),
                updatedItem.getCondition(),
                updatedItem.getStatus(),
                updatedItem.getUserId(),
                updatedItem.getCategory().getId()
        );


        log.info("found: {}", updatedItem);
        return inventoryRepository.findById(itemId).orElseThrow(
                () -> new ResourceNotFoundException("Item with id" + itemId + "doesn't exist!", itemId)
        );
    }

    private List<ItemResponse> convertToDtoList(List<Item> items){
        return  items.stream().map(itemMapper::toDto).toList();
    }

}
