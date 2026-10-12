package com.avdhoot.GroupSphereAPI.mapper;

import com.avdhoot.GroupSphereAPI.dto.reportDto.ReportStatusResponse;
import com.avdhoot.GroupSphereAPI.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(source = "id", target = "reportId")
    @Mapping(source = "targetUser.id", target = "targetMemberId")
    ReportStatusResponse toReportStatusResponse(Report report);

//    List<ReportStatusResponse> toListReportStatusResponse(List<Report> reports);
}
