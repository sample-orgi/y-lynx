package com.trss.bi.domain;

public enum Status {
    IN_PROGRESS,
    COMPLETED,
    ERRORED;
    public static boolean contains(String status) {
        for (Status s : Status.values()) {
            if (s.name().equals(status)) {
                return true;
            }
        }
        return false;
    }
}
