package com.example.inventory_service.repository;


import com.example.inventory_service.entity.Item;
import com.example.inventory_service.enums.ItemCondition;
import com.example.inventory_service.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InventoryRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByUserId(Long usedId);


    @Query("SELECT i FROM Item i WHERE "
            + "(:title IS NULL OR LOWER(i.title) LIKE LOWER(CONCAT('%', :title, '%'))) "
            + "AND (:categoryId IS NULL OR i.category.id = :categoryId) "
            + "AND (:condition IS NULL OR i.condition = :condition) "
            + "AND (:status IS NULL OR i.status = :status) "
            + "AND (:userId IS NULL OR i.userId = :userId) "
            + "AND (:minPrice IS NULL OR i.price >= :minPrice) "
            + "AND (:maxPrice IS NULL OR i.price <= :maxPrice) "
    )
    List<Item> searchItems(
            @Param("title") String title,
            @Param("categoryId") Long categoryId,
            @Param("condition") ItemCondition condition,
            @Param("status") ItemStatus status,
            @Param("userId") Long userId,
            @Param("minPrice") Float minPrice,
            @Param("maxPrice") Float maxPrice
    );

    @Query("SELECT i FROM Item i WHERE i.category.id = :categoryId")
    List<Item> findAllByCategoryId(@Param("categoryId") Long categoryId);

}