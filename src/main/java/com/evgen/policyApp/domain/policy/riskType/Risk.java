package com.evgen.policyApp.domain.policy.riskType;

import java.math.BigDecimal;
import java.util.Objects;

public class Risk {
    private String type;
    private BigDecimal coefficientBelowThreshold;
    private BigDecimal coefficientAboveThreshold;
    private BigDecimal threshold;

    public Risk(String type, BigDecimal coefficientBelowThreshold, BigDecimal coefficientAboveThreshold, BigDecimal threshold) {
        this.type = type;
        this.coefficientBelowThreshold = coefficientBelowThreshold;
        this.coefficientAboveThreshold = coefficientAboveThreshold;
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "Risk{" +
                "type='" + type + '\'' +
                ", coefficientBelowThreshold=" + coefficientBelowThreshold +
                ", coefficientAboveThreshold=" + coefficientAboveThreshold +
                ", threshold=" + threshold +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Risk risk = (Risk) o;
        return Objects.equals(type, risk.type) &&
                Objects.equals(coefficientBelowThreshold, risk.coefficientBelowThreshold) &&
                Objects.equals(coefficientAboveThreshold, risk.coefficientAboveThreshold) &&
                Objects.equals(threshold, risk.threshold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, coefficientBelowThreshold, coefficientAboveThreshold, threshold);
    }


}
