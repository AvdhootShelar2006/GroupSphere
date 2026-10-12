package com.avdhoot.GroupSphereAPI.mapper;

import com.avdhoot.GroupSphereAPI.dto.userDto.CreateUserDetailResponse;
import com.avdhoot.GroupSphereAPI.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    @Mapping(source = "user.id", target = "userId")
    CreateUserDetailResponse toMemberResponseDto(User user);
}
