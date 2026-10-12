package com.avdhoot.GroupSphereAPI.mapper;

import com.avdhoot.GroupSphereAPI.dto.groupDto.CreateGroupRequestDto;
import com.avdhoot.GroupSphereAPI.dto.groupDto.GroupResponseDto;
import com.avdhoot.GroupSphereAPI.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    @Mapping(source = "groupName", target = "groupName")
    Group toEntity(CreateGroupRequestDto dto);

    @Mapping(source = "groupName", target = "name")
    GroupResponseDto toGroupResponseDto(Group group);

//    List<GroupResponseDto> toGroupResponseDtoList(List<Group> group);
}
