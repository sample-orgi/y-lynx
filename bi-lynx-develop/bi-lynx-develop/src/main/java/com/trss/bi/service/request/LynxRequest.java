package com.trss.bi.service.request;

import com.trss.bi.service.request.enums.LynxRequestType;

import java.util.List;

public class LynxRequest {
    public String requestType;

    // Execute template criteria
    public String collabKey;
    public String templateId;
    public List<String> companyIds;

    // Search for templates criteria
    public String searchString;
    public String searchResultsLimit;
    public String searchPageNumber;
    public String searchOrderBy;
    public String searchDirection;
    public String searchAssetClass;

    public static LynxRequest getTemplatesRequest() {
        LynxRequest request = new LynxRequest();
        request.requestType = LynxRequestType.SEARCH_TEMPLATES.name();
        request.searchAssetClass = "TRSS";
        return request;
    }

    public static LynxRequest executeTemplateRequest(String collabKey, String templateId, List<String> companyIds) {
        LynxRequest request = new LynxRequest();
        request.requestType = LynxRequestType.EXECUTE_TEMPLATE.name();
        request.collabKey = collabKey;
        request.templateId = templateId;
        request.companyIds = companyIds;
        return request;
    }
}
