package com.example.user_service.service.impl;

import com.example.user_service.dto.FavoriteDto;
import com.example.user_service.dto.UserDto;
import com.example.user_service.entity.User;
import com.example.user_service.entity.Favorite;
import com.example.user_service.exception.ResourceNotFoundException;
import com.example.user_service.repository.FavoriteRepository;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.UserService;
import com.example.user_service.utils.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final UserMapper mapper;

    @Override
    public UserDto getUserById(Long userId) {
        return mapper.toDto(findUserById(userId));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("Found {} users", users.size());
        return convertToDtoList(users);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User existingUser = findUserById(userId);
        mapper.updateUserFromDto(userDto, existingUser);
        return mapper.toDto(userRepository.save(existingUser));
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(findUserById(userId));
    }

    @Override
    public void addFavorite(Long userId, Long itemId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!favoriteRepository.existsByUserIdAndItemId(userId, itemId)){
            Favorite favorite = Favorite.builder()
                    .user(user)
                    .itemId(itemId)
                    .build();
            favoriteRepository.save(favorite);
            log.info("Added favorite item {} for user {}", itemId, userId);
        } else {
            log.info("Favorite already exists for user {} and item {}", userId, itemId);
        }
    }

    @Transactional
    @Override
    public void removeFavorite(Long userId, Long itemId) {
        favoriteRepository.deleteByUserIdAndItemId(userId, itemId);
    }

    @Override
    public List<FavoriteDto> getAllUserFavorites(Long userId) {
        return convertToDtoList2(favoriteRepository.findAllByUserId(userId));
    }



    private User findUserById(Long userId){

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Item with id " + userId + " doesn't exist!"));
        log.info("found: {}", existingUser);
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with id" + userId + "doesn't exist!")
        );
    }


    private List<UserDto> convertToDtoList(List<User> users){
        return  users.stream().map(mapper::toDto).toList();
    }

    private List<FavoriteDto> convertToDtoList2(List<Favorite> favorites){
        return  favorites.stream().map(mapper::toDto).toList();
    }
}




