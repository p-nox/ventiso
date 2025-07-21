package com.example.user_service.service;


import com.example.user_service.dto.FavoriteDto;
import com.example.user_service.dto.UserDto;
import com.example.user_service.entity.Favorite;

import java.util.List;

public interface UserService {

    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long userId, UserDto userDto);
    void deleteUser(Long userId);

    void addFavorite(Long userId, Long itemId);
    void removeFavorite(Long userId, Long itemId);
    List<FavoriteDto> getAllUserFavorites(Long userId);
}
