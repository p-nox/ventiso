package com.example.user_service.controller;

import com.example.user_service.dto.FavoriteDto;
import com.example.user_service.dto.UserDto;
import com.example.user_service.entity.Favorite;
import com.example.user_service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
    @PostMapping("/{userId}/favorite/{itemId}")
    public ResponseEntity<Void> addFavorite(@PathVariable Long userId, @PathVariable Long itemId) {
        userService.addFavorite(userId, itemId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/favorite/{itemId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long userId, @PathVariable Long itemId) {
        userService.removeFavorite(userId, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<List<FavoriteDto>> getAllUserFavorites(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getAllUserFavorites(userId));
    }


}
