package com.example.user_service.utils;

import com.example.user_service.dto.FavoriteDto;
import com.example.user_service.dto.UserDto;
import com.example.user_service.entity.Favorite;
import com.example.user_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {


    User toEntity(UserDto dto);

    UserDto toDto(User entity);

    FavoriteDto toDto(Favorite entity);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateUserFromDto(UserDto dto, @MappingTarget User entity);

}
