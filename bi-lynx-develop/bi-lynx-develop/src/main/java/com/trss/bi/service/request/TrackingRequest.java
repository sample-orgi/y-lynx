package com.trss.bi.service.request;

import com.trss.bi.domain.Status;
import com.trss.bi.service.request.enums.TrackingRequestType;

import java.util.List;

public class TrackingRequest {
    public String requestType;
    public String clientId;
    public String requestUserId;
    public String requestUserName;
    public String templateId;
    public String templateName;
    public List<String> companyIds;

    // only used for updating the tracking service with an error
    public String collabKey;
    public String status;
    public String errorMessage;

    public TrackingRequest() {}

    public TrackingRequest(String clientId, String userId, String userName, String templateId, String templateName, List<String> companyIds) {
        this(TrackingRequestType.NEW, clientId, userId, userName, templateId, templateName, companyIds);
    }

    public TrackingRequest(TrackingRequestType trackingRequestType, String clientId, String userId, String userName,
                           String templateId, String templateName, List<String> companyIds) {
        this.requestType = trackingRequestType.name();
        this.clientId = clientId;
        this.requestUserId = userId;
        this.requestUserName = userName;
        this.templateId = templateId;
        this.templateName = templateName;
        this.companyIds = companyIds;
    }

    public static TrackingRequest errorRequest(String collabKey, String errorMessage) {
        TrackingRequest request = new TrackingRequest();
        request.requestType = TrackingRequestType.STATUS_UPDATE.name();
        request.collabKey = collabKey;
        request.status = Status.ERRORED.name();
        request.errorMessage = errorMessage;
        return request;
    }
}
