package com.avdhoot.GroupSphereAPI.dto.groupMemberDto;

import java.time.LocalDateTime;

public record LeaveResponseDto(
        Integer memberId,
        String name,
        Integer groupId,
        LocalDateTime leftAt
) {
}
