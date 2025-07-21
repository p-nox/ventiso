package com.example.inventory_service.response;

import com.example.inventory_service.entity.Category;
import com.example.inventory_service.enums.ItemCondition;
import com.example.inventory_service.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {

    private Long id;

    private String title;

    private String description;

    private Float price;

    private ItemCondition condition; // New-Used-Like new....

    private ItemStatus status;  //Active-sold-hidden...

    private Long userId;

    private Long categoryId;

    private Date createdAt;

    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
            createdAt = new Date();
    }

}
