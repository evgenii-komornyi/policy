package com.evgen.policyApp.dto;

import java.util.Objects;

public class ResponseDTO {
    private PolicyDTO policy;

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "policy=" + policy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDTO that = (ResponseDTO) o;
        return Objects.equals(policy, that.policy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policy);
    }

    public PolicyDTO getPolicy() {
        return policy;
    }

    public void setPolicy(PolicyDTO policy) {
        this.policy = policy;
    }
}
