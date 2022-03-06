package com.trss.bi.service.dto;

import java.util.Map;

public class LynxResponseMessageAttributesDto {

    private Map<String, String> organisationCode;
    private Map<String, String> correlationId;

    public Map<String, String> getOrganisationCode() {
        return organisationCode;
    }

    public void setOrganisationCode(Map<String, String> organisationCode) {
        this.organisationCode = organisationCode;
    }

    public Map<String, String> getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(Map<String, String> correlationId) {
        this.correlationId = correlationId;
    }
}
