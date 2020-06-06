package com.evgen.policyApp.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class PolicyDTO {
    private String number;
    private String status;
    private BigDecimal premium;
    private Map<String, BigDecimal> sumInsured;

    @Override
    public String toString() {
        return "PolicyDTO{" +
                "number='" + number + '\'' +
                ", status='" + status + '\'' +
                ", premium=" + premium +
                ", sumInsured=" + sumInsured +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyDTO policyDTO = (PolicyDTO) o;
        return Objects.equals(number, policyDTO.number) &&
                Objects.equals(status, policyDTO.status) &&
                Objects.equals(premium, policyDTO.premium) &&
                Objects.equals(sumInsured, policyDTO.sumInsured);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, status, premium, sumInsured);
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

    public Map<String, BigDecimal> getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(Map<String, BigDecimal> sumInsured) {
        this.sumInsured = sumInsured;
    }
}
