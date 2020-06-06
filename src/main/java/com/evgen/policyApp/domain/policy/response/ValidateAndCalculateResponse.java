package com.evgen.policyApp.domain.policy.response;

import java.util.HashSet;
import java.util.Objects;

public class ValidateAndCalculateResponse {
    private String number;
    private String status;
    private CalculateResponse calculateResponse;
    private HashSet<String> errors;

    @Override
    public String toString() {
        return "ValidateAndCalculateResponse{" +
                "number='" + number + '\'' +
                ", status='" + status + '\'' +
                ", calculateResponse=" + calculateResponse +
                ", errors=" + errors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidateAndCalculateResponse that = (ValidateAndCalculateResponse) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(status, that.status) &&
                Objects.equals(calculateResponse, that.calculateResponse) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, status, calculateResponse, errors);
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

    public CalculateResponse getCalculateResponse() {
        return calculateResponse;
    }

    public void setCalculateResponse(CalculateResponse calculateResponse) {
        this.calculateResponse = calculateResponse;
    }

    public HashSet<String> getErrors() {
        return errors;
    }

    public void setErrors(HashSet<String> errors) {
        this.errors = errors;
    }
}
