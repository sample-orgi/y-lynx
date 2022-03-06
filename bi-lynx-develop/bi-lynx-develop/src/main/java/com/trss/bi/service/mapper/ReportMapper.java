package com.trss.bi.service.mapper;

import com.trss.bi.domain.Report;
import com.trss.bi.service.dto.ReportDTO;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public class ReportMapper extends BaseEntityMapper<ReportDTO, Report> {

    @Override
    public Report toEntity(ReportDTO dto) {
        if (dto == null) {
            return null;
        } else {
            Report entity = new Report();
            if (dto.getId() != null) {
                entity.setId(new ObjectId(dto.getId()));
            }

            entity.setCollabKey(dto.getCollabKey());
            entity.setTemplateId(dto.getTemplateId());
            entity.setTemplateName(dto.getTemplateName());
            entity.setCompanyIds(dto.getCompanyIds());
            entity.setUserId(dto.getUserId());
            entity.setCustomerId(dto.getCustomerId());
            entity.setStatus(dto.getStatus());
            entity.setMessage(dto.getMessage());
            entity.setXhtml(dto.getXhtml());
            entity.setTrssCreatedDate(dto.getTrssCreatedDate());
            entity.setTrssUpdatedDate(dto.getTrssUpdatedDate());
            entity.setCreatedDate(dto.getCreatedDate());

            return entity;
        }
    }

    @Override
    public ReportDTO toDto(Report entity) {
        if (entity == null) {
            return null;
        } else {
            ReportDTO dto = new ReportDTO();
            if (entity.getId() != null) {
                dto.setId(entity.getId().toString());
            }

            dto.setCollabKey(entity.getCollabKey());
            dto.setTemplateId(entity.getTemplateId());
            dto.setTemplateName(entity.getTemplateName());
            dto.setCompanyIds(entity.getCompanyIds());
            dto.setUserId(entity.getUserId());
            dto.setCustomerId(entity.getCustomerId());
            dto.setStatus(entity.getStatus());
            dto.setMessage(entity.getMessage());
            dto.setXhtml(entity.getXhtml());
            dto.setTrssCreatedDate(entity.getTrssCreatedDate());
            dto.setTrssUpdatedDate(entity.getTrssUpdatedDate());
            dto.setCreatedDate(entity.getCreatedDate());

            return dto;
        }
    }
}
