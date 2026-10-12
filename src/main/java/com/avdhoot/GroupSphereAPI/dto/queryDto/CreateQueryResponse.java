package com.avdhoot.GroupSphereAPI.dto.queryDto;

public record CreateQueryResponse(
         String title,
         String description,
         String postedByName,
         Integer groupId,
         boolean isResolved
) {
}
