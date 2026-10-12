package com.avdhoot.GroupSphereAPI.dto.userDto;

import java.time.LocalDate;

public record CreateUserDetailResponse(
        Integer userId,
        String name,
        String email,
        String username,
        LocalDate createdAt
) {
}
