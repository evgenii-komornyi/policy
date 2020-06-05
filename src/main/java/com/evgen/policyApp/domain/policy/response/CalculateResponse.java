package com.evgen.policyApp.domain.policy.response;

import java.math.BigDecimal;
import java.util.Objects;

public class CalculateResponse {
    private java.lang.String number;
    private String status;

    private BigDecimal premium;

    @Override
    public java.lang.String toString() {
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

    public java.lang.String getNumber() {
        return number;
    }

    public void setNumber(java.lang.String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }
}
