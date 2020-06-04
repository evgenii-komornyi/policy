package com.evgen.policyApp.domain.policy.request;

import com.evgen.policyApp.domain.policy.PolicyStatus;

import java.util.List;
import java.util.Objects;

public class PolicyRequest {
    private String number;
    private PolicyStatus status;
    private List<ObjectRequest> objects;

    @Override
    public String toString() {
        return "PolicyRequest{" +
                "number='" + number + '\'' +
                ", status=" + status +
                ", objects=" + objects +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyRequest that = (PolicyRequest) o;
        return Objects.equals(number, that.number) &&
                status == that.status &&
                Objects.equals(objects, that.objects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, status, objects);
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

    public List<ObjectRequest> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectRequest> objects) {
        this.objects = objects;
    }
}
