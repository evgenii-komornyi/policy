package com.evgen.policyApp.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class PolicyDTO {
    private String number;
    private String status;
    private BigDecimal premium;
    private List<SumInsuredByRiskTypeDTO> sumInsured;
    private HashSet<String> errors;

    @Override
    public String toString() {
        return "PolicyDTO{" +
                "number='" + number + '\'' +
                ", status='" + status + '\'' +
                ", premium=" + premium +
                ", sumInsured=" + sumInsured +
                ", errors=" + errors +
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
                Objects.equals(sumInsured, policyDTO.sumInsured) &&
                Objects.equals(errors, policyDTO.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, status, premium, sumInsured, errors);
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

    public List<SumInsuredByRiskTypeDTO> getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(List<SumInsuredByRiskTypeDTO> sumInsured) {
        this.sumInsured = sumInsured;
    }

    public HashSet<String> getErrors() {
        return errors;
    }

    public void setErrors(HashSet<String> errors) {
        this.errors = errors;
    }
}
