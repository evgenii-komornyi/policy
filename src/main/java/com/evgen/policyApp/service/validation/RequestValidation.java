package com.evgen.policyApp.service.validation;

import com.evgen.policyApp.domain.policy.request.ObjectRequest;
import com.evgen.policyApp.domain.policy.request.PolicyRequest;
import com.evgen.policyApp.domain.policy.request.SubObjectRequest;
import com.evgen.policyApp.repository.RiskRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RequestValidation {
    private RiskRepository repository = new RiskRepository();

    public HashSet<String> validateRequest(PolicyRequest request) {
        HashSet<String> allErrors = new HashSet<>();

        allErrors.addAll(validateNumber(request.getNumber()));
        allErrors.addAll(validateStatus(request.getStatus()));
        allErrors.addAll(validateObjects(request.getObjects()));
        return allErrors;
    }

    private List<String> validateNumber(String number) {
        List<String> errorsList = new ArrayList<>();

        if (number.isEmpty() || number == null) {
            errorsList.add("Number can't be empty");
        }
        return errorsList;
    }

    private List<String> validateStatus(String status) {
        List<String> errorsList = new ArrayList<>();

        if (status.isEmpty() || status == null) {
            errorsList.add("Status cant't be empty");
        }

        return errorsList;
    }

    private List<String> validateObjects(List<ObjectRequest> objects) {
        List<String> errorsList = new ArrayList<>();

        for (ObjectRequest object : objects) {
            errorsList.addAll(validateObjectName(object.getName()));
            errorsList.addAll(validateObjectItems(object.getItems()));
        }
        return errorsList;
    }

    private List<String> validateObjectName(String objectName) {
        List<String> errorsList = new ArrayList<>();

        if (objectName.isEmpty() || objectName == null) {
            errorsList.add("Object name can't be empty");
        }
        return errorsList;
    }

    private List<String> validateObjectItems(List<SubObjectRequest> items) {
        List<String> errorsList = new ArrayList<>();

        if (items.size() == 0) {
            errorsList.add("Items can't be empty");
        }

        for (SubObjectRequest item : items) {
            if (item.getName().isEmpty() || item.getName() == null) {
                errorsList.add("Item name can't be empty");
            }

            if (item.getCost() == null) {
                errorsList.add("Cost can't be empty");
            } else if (item.getCost().compareTo(BigDecimal.ZERO) < 0) {
                errorsList.add("Cost can't be negative");
            }

            errorsList.addAll(validateRisks(item.getRisks()));
        }

        return errorsList;
    }

    private List<String> validateRisks(List<String> risks) {
        List<String> errorsList = new ArrayList<>();

        if (risks.isEmpty()) {
            errorsList.add("Risk can't be empty");
        }

        for (String s : repository.getAllRisks().keySet()) {
            if (!risks.contains(s)) {
                errorsList.add("Such risk doesn't exist");
            }
        }
        return errorsList;
    }
}
