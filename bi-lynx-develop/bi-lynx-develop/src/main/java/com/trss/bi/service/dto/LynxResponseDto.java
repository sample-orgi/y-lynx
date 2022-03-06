package com.trss.bi.service.dto;

public class LynxResponseDto {

    protected String type;
    protected String messageId;
    protected String topicArn;
    protected String message;
    protected String timestamp;
    protected String signatureVersion;
    protected String signature;
    protected String signingCertUrl;
    protected String unsubscribeUrl;
    protected LynxResponseMessageAttributesDto messageAttributes;
    protected ReportRestorePropertiesDto reportRestoreProperties;

    public ReportRestorePropertiesDto getReportRestoreProperties() {
        return reportRestoreProperties;
    }

    public void setReportRestoreProperties(ReportRestorePropertiesDto reportRestoreProperties) {
        this.reportRestoreProperties = reportRestoreProperties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTopicArn() {
        return topicArn;
    }

    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignatureVersion() {
        return signatureVersion;
    }

    public void setSignatureVersion(String signatureVersion) {
        this.signatureVersion = signatureVersion;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSigningCertUrl() {
        return signingCertUrl;
    }

    public void setSigningCertUrl(String signingCertUrl) {
        this.signingCertUrl = signingCertUrl;
    }

    public String getUnsubscribeUrl() {
        return unsubscribeUrl;
    }

    public void setUnsubscribeUrl(String unsubscribeUrl) {
        this.unsubscribeUrl = unsubscribeUrl;
    }

    public LynxResponseMessageAttributesDto getMessageAttributes() {
        return messageAttributes;
    }

    public void setMessageAttributes(LynxResponseMessageAttributesDto messageAttributes) {
        this.messageAttributes = messageAttributes;
    }

    @Override
    public String toString() {
        return "LynxResponseDto{" +
                "type='" + type + '\'' +
                ", messageId='" + messageId + '\'' +
                ", topicArn='" + topicArn + '\'' +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", signatureVersion='" + signatureVersion + '\'' +
                ", signature='" + signature + '\'' +
                ", signingCertUrl='" + signingCertUrl + '\'' +
                ", unsubscribeUrl='" + unsubscribeUrl + '\'' +
                ", messageAttributes=" + messageAttributes +
                '}';
    }
}
