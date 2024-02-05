package com.guardianx.domain.mapper;

import com.guardianx.domain.dto.request.role.RoleRequestDto;
import com.guardianx.domain.dto.response.role.RoleResponseDto;
import com.guardianx.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleResponseDto toDto(Role user);
    Role toUser(RoleRequestDto userDto);
}
