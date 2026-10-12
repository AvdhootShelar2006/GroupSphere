package com.avdhoot.GroupSphereAPI.mapper;

import com.avdhoot.GroupSphereAPI.dto.groupMemberDto.GroupMemberDetailsResponse;
import com.avdhoot.GroupSphereAPI.dto.groupMemberDto.JoinGroupResponse;
import com.avdhoot.GroupSphereAPI.entity.Group;
import com.avdhoot.GroupSphereAPI.entity.GroupMembership;
import com.avdhoot.GroupSphereAPI.entity.User;
import com.avdhoot.GroupSphereAPI.enums.GroupRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MembershipMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    GroupMemberDetailsResponse toGroupMemberDetailsResponse(GroupMembership groupMembership);


//    List<GroupMemberDetailsResponse> toGroupMemberDetailsResponses(List<GroupMembership> groupMemberships);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "user", target = "user")
    @Mapping(source = "group", target = "group")
    @Mapping(target = "joinedAt",expression = "java(java.time.LocalDate.now())")
    @Mapping(source = "groupRole", target = "groupRole")
    GroupMembership createMembership(User user, Group group, GroupRole groupRole);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "groupMembership.joinedAt", target = "joinedAt")
    JoinGroupResponse toJoinGroupResponse(User user, Group group, GroupMembership groupMembership);
}
