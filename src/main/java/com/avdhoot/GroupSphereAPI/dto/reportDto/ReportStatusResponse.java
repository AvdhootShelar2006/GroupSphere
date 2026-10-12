package com.avdhoot.GroupSphereAPI.dto.reportDto;

import com.avdhoot.GroupSphereAPI.enums.ReportStatus;

import java.time.LocalDateTime;

public record ReportStatusResponse(
        Integer reportId,
        Integer targetMemberId,
        String reason,
        ReportStatus status,
        LocalDateTime reportedTime
) {
}
