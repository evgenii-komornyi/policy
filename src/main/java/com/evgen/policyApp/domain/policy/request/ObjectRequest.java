package com.evgen.policyApp.domain.policy.request;

import java.util.List;
import java.util.Objects;

public class ObjectRequest {
    private String name;
    private List<SubObjectRequest> items;

    @Override
    public String toString() {
        return "ObjectRequest{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectRequest that = (ObjectRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, items);
    }

    public List<SubObjectRequest> getItems() {
        return items;
    }

    public void setItems(List<SubObjectRequest> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
