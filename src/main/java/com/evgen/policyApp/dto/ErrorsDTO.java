package com.evgen.policyApp.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ErrorsDTO {
    private HashSet<String> errors;

    @Override
    public String toString() {
        return "ErrorsDTO{" +
                "errors=" + errors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorsDTO errorsDTO = (ErrorsDTO) o;
        return Objects.equals(errors, errorsDTO.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }

    public HashSet<String> getErrors() {
        return errors;
    }

    public void setErrors(HashSet<String> errors) {
        this.errors = errors;
    }
}
