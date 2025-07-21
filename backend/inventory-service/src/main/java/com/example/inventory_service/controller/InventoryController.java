package com.example.inventory_service.controller;


import com.example.inventory_service.enums.ItemCondition;
import com.example.inventory_service.enums.ItemStatus;
import com.example.inventory_service.response.ItemResponse;
import com.example.inventory_service.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;


    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long itemId){
        return ResponseEntity.ok(inventoryService.getItemById(itemId));
    }

    // δεν εχει user not found check
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ItemResponse>> getUserItems(@PathVariable Long userId){
        return ResponseEntity.ok(inventoryService.getAllUserItems(userId));
    }



    @GetMapping("/search")
    public ResponseEntity<?> searchItems(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) ItemCondition condition,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) ItemStatus status,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice
    ) {
        List<ItemResponse> result = inventoryService.searchItems(
                title,
                categoryId,
                condition,
                status,
                userId,
                minPrice,
                maxPrice);

        if( result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No items found matching your criteria");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody ItemResponse item){
        inventoryService.createItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added!");
    }


    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId){
        inventoryService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{itemId}")
    public ResponseEntity<ItemResponse> updateItem(@PathVariable Long itemId, @RequestBody ItemResponse item){
        log.info("Controller Fetching item with id {}", itemId);
        return ResponseEntity.ok(inventoryService.updateItem(itemId, item));
    }


    @PatchMapping("/{itemId}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long itemId, @RequestParam ItemStatus status) {
        inventoryService.updateStatus(itemId, status);
        return ResponseEntity.ok().build();
    }






}
