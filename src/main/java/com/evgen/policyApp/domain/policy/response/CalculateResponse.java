package com.evgen.policyApp.domain.policy.response;

import com.evgen.policyApp.domain.policy.Policy;
import com.evgen.policyApp.domain.policy.PolicyStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CalculateResponse {
    private String number;
    private PolicyStatus status;

    private BigDecimal premium;

    @Override
    public String toString() {
        return "CalculateResponse{" +
                "number='" + number + '\'' +
                ", status=" + status +
                ", premium=" + premium +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CalculateResponse that = (CalculateResponse) o;
        return Objects.equals(number, that.number) &&
                status == that.status &&
                Objects.equals(premium, that.premium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, status, premium);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public void setStatus(PolicyStatus status) {
        this.status = status;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }
}
