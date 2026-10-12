package com.avdhoot.GroupSphereAPI.dto.reportDto;

import com.avdhoot.GroupSphereAPI.enums.ReportStatus;

public record ReportStatusRequest(
        ReportStatus status
) {
}
