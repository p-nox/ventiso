package com.example.user_service.dto;

import com.example.user_service.entity.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {

    private Long id;
    private UserDto user;
    private Long itemId;

}