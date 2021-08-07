package com.isoft.nbawebsite.constants;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum SuspensionPeriod {
    ONE_WEEK("1 Week"),
    SIX_MONTH("6 Months"),
    LIFETIME("Lifetime");

    static Map<String, SuspensionPeriod> suspensionPeriodMap = new HashMap<>();

    static {
        for (SuspensionPeriod suspensionPeriod : SuspensionPeriod.values()) {
            suspensionPeriodMap.put(suspensionPeriod.label, suspensionPeriod);
        }
    }

    public final String label;

    SuspensionPeriod(String label) {
        this.label = label;
    }

    public static Optional<SuspensionPeriod> getSuspensionPeriodByLabel(String label) {
        return Optional.ofNullable(suspensionPeriodMap.get(label));
    }
}
