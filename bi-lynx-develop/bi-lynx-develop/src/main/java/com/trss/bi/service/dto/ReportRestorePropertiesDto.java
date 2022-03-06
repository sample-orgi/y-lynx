package com.trss.bi.service.dto;

import java.util.List;

public class ReportRestorePropertiesDto {

    protected String collabKey;
    protected String clientId;
    protected Long createdDate;
    protected Long lastUpdatedDate;
    protected List<String> companyIds;
    protected String requestUserId;
    protected String requestUserName;
    protected String templateId;
    protected String templateName;

    public ReportRestorePropertiesDto(String collabKey, String clientId, Long createdDate, Long lastUpdatedDate,
                                      List<String> companyIds, String requestUserId, String requestUserName,
                                      String templateId, String templateName) {
        this.collabKey = collabKey;
        this.clientId = clientId;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.companyIds = companyIds;
        this.requestUserId = requestUserId;
        this.requestUserName = requestUserName;
        this.templateId = templateId;
        this.templateName = templateName;
    }

    public String getCollabKey() {
        return collabKey;
    }

    public void setCollabKey(String collabKey) {
        this.collabKey = collabKey;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    public String getRequestUserId() {
        return requestUserId;
    }

    public String getRequestUserName() {
        return requestUserName;
    }

    public void setRequestUserName(String requestUserName) {
        this.requestUserName = requestUserName;
    }

    public void setRequestUserId(String requestUserId) {
        this.requestUserId = requestUserId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
