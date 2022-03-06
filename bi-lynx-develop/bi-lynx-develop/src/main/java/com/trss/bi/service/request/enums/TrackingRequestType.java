package com.trss.bi.service.request.enums;

public enum TrackingRequestType {
    NEW,
    STATUS_UPDATE;
    public static boolean contains(String type) {
        for (TrackingRequestType trt : TrackingRequestType.values()) {
            if (trt.name().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
