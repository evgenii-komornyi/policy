package com.evgen.policyApp.dto;

import com.evgen.policyApp.domain.policy.PolicyStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class PolicyDTO {
    private String number;
    private PolicyStatus status;
    private BigDecimal premium;
    private List<String> errors;

    @Override
    public String toString() {
        return "PolicyDTO{" +
                "number='" + number + '\'' +
                ", status=" + status +
                ", premium=" + premium +
                ", errors=" + errors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyDTO policyDTO = (PolicyDTO) o;
        return Objects.equals(number, policyDTO.number) &&
                status == policyDTO.status &&
                Objects.equals(premium, policyDTO.premium) &&
                Objects.equals(errors, policyDTO.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, status, premium, errors);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
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
