package com.evgen.policyApp.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class SumInsuredByRiskTypeDTO {
    private String riskType;
    private BigDecimal sumInsured;

    @Override
    public String toString() {
        return "SumInsuredByRiskTypeDTO{" +
                "riskType='" + riskType + '\'' +
                ", sumInsured=" + sumInsured +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SumInsuredByRiskTypeDTO that = (SumInsuredByRiskTypeDTO) o;
        return Objects.equals(riskType, that.riskType) &&
                Objects.equals(sumInsured, that.sumInsured);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riskType, sumInsured);
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }
}
