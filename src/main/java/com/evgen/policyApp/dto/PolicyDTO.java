package com.evgen.policyApp.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;

public class PolicyDTO {
    private String number;
    private String status;
    private BigDecimal premium;

    @Override
    public String toString() {
        return "PolicyDTO{" +
                "number='" + number + '\'' +
                ", status=" + status +
                ", premium=" + premium +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyDTO policyDTO = (PolicyDTO) o;
        return Objects.equals(number, policyDTO.number) &&
                status == policyDTO.status &&
                Objects.equals(premium, policyDTO.premium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, status, premium);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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
