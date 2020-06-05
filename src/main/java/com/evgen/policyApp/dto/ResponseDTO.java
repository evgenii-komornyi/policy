package com.evgen.policyApp.dto;

import java.util.Objects;

public class ResponseDTO {
    private PolicyDTO policy;
    private ErrorsDTO errors;

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "policy=" + policy +
                ", errors=" + errors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDTO that = (ResponseDTO) o;
        return Objects.equals(policy, that.policy) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policy, errors);
    }

    public PolicyDTO getPolicy() {
        return policy;
    }

    public void setPolicy(PolicyDTO policy) {
        this.policy = policy;
    }

    public ErrorsDTO getErrors() {
        return errors;
    }

    public void setErrors(ErrorsDTO errors) {
        this.errors = errors;
    }
}
