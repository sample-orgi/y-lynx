package com.trss.bi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Document(collection = "report")
public class Report extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    @NotNull
    @Field("collab_key")
    private String collabKey;

    @NotNull
    @Field("template_id")
    private String templateId;

    @NotNull
    @Field("template_name")
    private String templateName;

    @NotNull
    @Field("company_ids")
    private List<String> companyIds;

    @NotNull
    @Field("user_id")
    private Long userId;

    @Field("customer_id")
    private Long customerId;

    @NotNull
    @Field("status")
    private Status status = Status.IN_PROGRESS;

    @Field("message")
    private String message;

    @Field("xhtml")
    private String xhtml;

    @Field("trss_created_date")
    private Instant trssCreatedDate;

    @Field("trss_updated_date")
    private Instant trssUpdatedDate;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Report id(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getCollabKey() {
        return collabKey;
    }

    public void setCollabKey(String collabKey) {
        this.collabKey = collabKey;
    }

    public Report collabKey(String collabKey) {
        setCollabKey(collabKey);
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Report templateId(String templateId) {
        setTemplateId(templateId);
        return this;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Report templateName(String templateName) {
        setTemplateName(templateName);
        return this;
    }

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    public Report companyIds(List<String> companyIds) {
        setCompanyIds(companyIds);
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Report userId(Long userId) {
        setUserId(userId);
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Report customerId(Long customerId) {
        setCustomerId(customerId);
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Report status(Status status) {
        setStatus(status);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Report message(String message) {
        setMessage(message);
        return this;
    }

    public String getXhtml() {
        return xhtml;
    }

    public void setXhtml(String xhtml) {
        this.xhtml = xhtml;
    }

    public Report xhtml(String xhtml) {
        setXhtml(xhtml);
        return this;
    }

    public Instant getTrssCreatedDate() {
        return trssCreatedDate;
    }

    public void setTrssCreatedDate(Instant trssCreatedDate) {
        this.trssCreatedDate = trssCreatedDate;
    }

    public Report trssCreatedDate(Instant trssCreatedDate) {
        setTrssCreatedDate(trssCreatedDate);
        return this;
    }

    public Instant getTrssUpdatedDate() {
        return trssUpdatedDate;
    }

    public void setTrssUpdatedDate(Instant trssUpdatedDate) {
        this.trssUpdatedDate = trssUpdatedDate;
    }

    public Report trssUpdatedDate(Instant trssUpdatedDate) {
        setTrssUpdatedDate(trssUpdatedDate);
        return this;
    }

    @Override
    public String toString() {
        return "Report{" +
            "id=" + id +
            ", collabKey='" + collabKey + '\'' +
            ", templateId='" + templateId + '\'' +
            ", templateName='" + templateName + '\'' +
            ", companyIds=" + companyIds +
            ", userId=" + userId +
            ", customerId=" + customerId +
            ", status=" + status +
            ", message='" + message + '\'' +
            ", xhtml='" + xhtml + '\'' +
            ", trssCreatedDate=" + trssCreatedDate +
            ", trssUpdatedDate=" + trssUpdatedDate +
            '}';
    }
}
