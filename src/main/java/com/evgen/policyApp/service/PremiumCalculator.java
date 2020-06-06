package com.evgen.policyApp.service;

import com.evgen.policyApp.domain.policy.Policy;
import com.evgen.policyApp.domain.policy.PolicyObject;
import com.evgen.policyApp.domain.policy.PolicySubObject;
import com.evgen.policyApp.domain.policy.Risk;
import com.evgen.policyApp.domain.policy.request.ObjectRequest;
import com.evgen.policyApp.domain.policy.request.PolicyRequest;
import com.evgen.policyApp.domain.policy.request.SubObjectRequest;
import com.evgen.policyApp.domain.policy.response.CalculateResponse;
import com.evgen.policyApp.domain.policy.response.ValidateAndCalculateResponse;
import com.evgen.policyApp.repository.RiskRepository;
import com.evgen.policyApp.service.validation.RequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class PremiumCalculator {
    private final RiskRepository repository;
    private final RequestValidation validation;

    @Autowired
    public PremiumCalculator(RiskRepository repository, RequestValidation validation) {
        this.repository = repository;
        this.validation = validation;
    }

    public ValidateAndCalculateResponse validateAndCalculate(PolicyRequest request) throws IOException {
        ValidateAndCalculateResponse response = new ValidateAndCalculateResponse();
        HashSet<String> errors = validation.validateRequest(request);

        if (errors.isEmpty()) {
            response.setNumber(request.getNumber());
            response.setStatus(request.getStatus());
            Policy policy = convertToObject(request);
            response.setCalculateResponse(calculate(policy));
        } else {
            response.setErrors(errors);
        }

        return response;
    }

    private Policy convertToObject(PolicyRequest request) {
        Policy policy = new Policy();

        List<PolicyObject> objects = new ArrayList<>();

        for (ObjectRequest requestObject : request.getObjects()) {
            PolicyObject object = new PolicyObject();

            object.setObjectName(requestObject.getName());
            List<PolicySubObject> items = new ArrayList<>();

            for (SubObjectRequest requestItem : requestObject.getItems()) {
                PolicySubObject item = new PolicySubObject();
                List<Risk> riskList = new ArrayList<>();

                List<String> riskWithoutDuplicate = removeDuplicate(requestItem.getRisks());

                item.setSubObjectName(requestItem.getName());
                item.setCostOfTheItem(requestItem.getCost());

                for (String risk : riskWithoutDuplicate) {
                    for (Map.Entry<String, Risk> s : repository.getAllRisks().entrySet()) {
                        if (risk.contains(s.getKey())) {
                            riskList.add(s.getValue());
                            item.setRiskType(riskList);
                        }
                    }
                }
                items.add(item);

                object.setSubObjects(items);
            }

            objects.add(object);
        }

        policy.setObjects(objects);
        policy.setStatus(request.getStatus().toUpperCase());
        policy.setNumber(request.getNumber());

        return policy;
    }

    private List<String> removeDuplicate(List<String> riskList) {
        List<String> risksWithoutDuplicate = new ArrayList<>();

        for (String risk : riskList) {
            if (!risksWithoutDuplicate.contains(risk.toUpperCase())) {
                risksWithoutDuplicate.add(risk.toUpperCase());
            }
        }
        return risksWithoutDuplicate;
    }

    private CalculateResponse calculate(Policy policy) throws IOException {
        Map<Risk, BigDecimal> amountByRisk = new HashMap<>();

        for (PolicyObject object : policy.getObjects()) {
            for (PolicySubObject item : object.getSubObjects()) {
                for (Risk risk : item.getRiskType()) {
                    amountByRisk.computeIfPresent(risk, (key, val) -> val.add(item.getCostOfTheItem()));
                    amountByRisk.putIfAbsent(risk, item.getCostOfTheItem());
                }
            }
        }
        Map<String, BigDecimal> sumInsured = new HashMap<>();

        for (Map.Entry<Risk, BigDecimal> entry : amountByRisk.entrySet()) {
            sumInsured.put(entry.getKey().getRiskType(), entry.getValue());
        }

        Map<String, BigDecimal> premiumByRisk = new HashMap<>();

        for (Map.Entry<Risk, BigDecimal> entry : amountByRisk.entrySet()) {
            premiumByRisk.put(entry.getKey().getRiskType(), entry.getValue().multiply(entry.getKey()
                    .getCurrentCoefficient(entry.getValue())).setScale(2, RoundingMode.HALF_EVEN));
        }

        BigDecimal premium = new BigDecimal("0.00");

        for (BigDecimal value : premiumByRisk.values()) {
            premium = premium.add(value);
        }

        CalculateResponse response = new CalculateResponse();
        response.setPremium(premium);
        response.setAmountByRisk(sumInsured);
        return response;
    }
}
