package com.example.user_service.repository;

import com.example.user_service.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    boolean existsByUserIdAndItemId(Long userId, Long itemId);
    void deleteByUserIdAndItemId(Long userId, Long itemId);
    List<Favorite> findAllByUserId(Long userId);
}
