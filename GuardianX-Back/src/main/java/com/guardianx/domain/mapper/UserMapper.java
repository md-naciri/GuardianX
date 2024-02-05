package com.guardianx.domain.mapper;

import com.guardianx.domain.dto.request.user.UserRequestDto;
import com.guardianx.domain.dto.response.user.UserResponseDto;
import com.guardianx.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserResponseDto toDto(User user);
    User toUser(UserRequestDto userDto);
}
