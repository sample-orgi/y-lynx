package com.trss.bi.service.dto;

import com.trss.bi.domain.Status;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class ReportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String collabKey;
    private String templateId;
    private String templateName;
    private List<String> companyIds;
    private Long userId;
    private Long customerId;
    private Status status = Status.IN_PROGRESS;
    private String message;
    private String xhtml;
    private Instant trssCreatedDate;
    private Instant trssUpdatedDate;
    private Instant createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReportDTO id(String id) {
        this.id = id;
        return this;
    }

    public String getCollabKey() {
        return collabKey;
    }

    public void setCollabKey(String collabKey) {
        this.collabKey = collabKey;
    }

    public ReportDTO collabKey(String collabKey) {
        setCollabKey(collabKey);
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public ReportDTO templateId(String templateId) {
        setTemplateId(templateId);
        return this;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public ReportDTO templateName(String templateName) {
        setTemplateName(templateName);
        return this;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    public ReportDTO companyIds(List<String> companyIds) {
        setCompanyIds(companyIds);
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ReportDTO userId(Long userId) {
        setUserId(userId);
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public ReportDTO customerId(Long customerId) {
        setCustomerId(customerId);
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ReportDTO status(Status status) {
        setStatus(status);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReportDTO message(String message) {
        setMessage(message);
        return this;
    }

    public String getXhtml() {
        return xhtml;
    }

    public void setXhtml(String xhtml) {
        this.xhtml = xhtml;
    }

    public ReportDTO xhtml(String xhtml) {
        setXhtml(xhtml);
        return this;
    }

    public Instant getTrssCreatedDate() {
        return trssCreatedDate;
    }

    public void setTrssCreatedDate(Instant trssCreatedDate) {
        this.trssCreatedDate = trssCreatedDate;
    }

    public ReportDTO trssCreatedDate(Instant trssCreatedDate) {
        setTrssCreatedDate(trssCreatedDate);
        return this;
    }

    public Instant getTrssUpdatedDate() {
        return trssUpdatedDate;
    }

    public void setTrssUpdatedDate(Instant trssUpdatedDate) {
        this.trssUpdatedDate = trssUpdatedDate;
    }

    public ReportDTO trssUpdatedDate(Instant trssUpdatedDate) {
        setTrssUpdatedDate(trssUpdatedDate);
        return this;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public ReportDTO createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
            "id=" + id +
            ", collabKey='" + collabKey + '\'' +
            ", templateId='" + templateId + '\'' +
            ", templateName='" + templateName + '\'' +
            ", companyIds=" + companyIds +
            ", userId=" + userId +
            ", customerId=" + customerId +
            ", status=" + status +
            ", message='" + message + '\'' +
            ", trssCreatedDate=" + trssCreatedDate +
            ", trssUpdatedDate=" + trssUpdatedDate +
            ", createdDate=" + createdDate +
            '}';
    }
}
