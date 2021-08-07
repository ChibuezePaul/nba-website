package com.isoft.nbawebsite.constants;

import java.util.HashMap;
import java.util.Map;

public enum PaymentStatus {
    COMPLETED,
    FAILED;

    private static Map<String, PaymentStatus> statuses = new HashMap<>();

    static {
        statuses.put("completed", COMPLETED);
        statuses.put("failed", FAILED);
    }

    public static PaymentStatus of(String status) {
        return statuses.get(status);
    }
}
