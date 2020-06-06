package com.evgen.policyApp.domain.policy.response;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class CalculateResponse {
    private BigDecimal premium;
    private Map<String, BigDecimal> amountByRisk;

    @Override
    public String toString() {
        return "CalculateResponse{" +
                "premium=" + premium +
                ", amountByRisk=" + amountByRisk +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculateResponse that = (CalculateResponse) o;
        return Objects.equals(premium, that.premium) &&
                Objects.equals(amountByRisk, that.amountByRisk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(premium, amountByRisk);
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public Map<String, BigDecimal> getAmountByRisk() {
        return amountByRisk;
    }

    public void setAmountByRisk(Map<String, BigDecimal> amountByRisk) {
        this.amountByRisk = amountByRisk;
    }
}
