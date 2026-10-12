package com.avdhoot.GroupSphereAPI.dto.groupMemberDto;

import java.time.LocalDate;

public record GroupMemberDetailsResponse (
        Integer userId,
        String username,
        LocalDate joinedAt
){
}
