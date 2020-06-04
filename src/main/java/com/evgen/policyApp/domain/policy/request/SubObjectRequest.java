package com.evgen.policyApp.domain.policy.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class SubObjectRequest {
    private String name;
    private BigDecimal cost;
    private List<String> risks;

    @Override
    public String toString() {
        return "SubObjectRequest{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", risks=" + risks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubObjectRequest that = (SubObjectRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(risks, that.risks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, risks);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public List<String> getRisks() {
        return risks;
    }

    public void setRisks(List<String> risks) {
        this.risks = risks;
    }
}
