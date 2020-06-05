package com.evgen.policyApp.service;

import com.evgen.policyApp.domain.policy.Policy;
import com.evgen.policyApp.domain.policy.PolicyObject;
import com.evgen.policyApp.domain.policy.PolicySubObject;
import com.evgen.policyApp.domain.policy.Risk;
import com.evgen.policyApp.domain.policy.response.CalculateResponse;
import com.evgen.policyApp.repository.RiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
public class PremiumCalculator {
    private final RiskRepository repository;

    @Autowired
    public PremiumCalculator(RiskRepository repository) {
        this.repository = repository;
    }

    public CalculateResponse calculate(Policy policy) throws IOException {
        CalculateResponse response = new CalculateResponse();

        Map<Risk, BigDecimal> amountByRisk = new HashMap<>();

        for (PolicyObject object : policy.getObjects()) {
            for (PolicySubObject item : object.getSubObjects()) {

                for (Risk risk : item.getRiskType()) {
                    amountByRisk.computeIfPresent(risk, (key, val) -> val.add(item.getCostOfTheItem()));
                    amountByRisk.putIfAbsent(risk, item.getCostOfTheItem());

                    System.out.println("AmountByRisk: " + amountByRisk);
                }
            }
        }

        Map<String, BigDecimal> premiumByRisk = new HashMap<>();

        for (Map.Entry<Risk, BigDecimal> entry : amountByRisk.entrySet()) {
            System.out.println("Sum: " + entry.getValue());
            System.out.println("RiskType: " + entry.getKey().getRiskType());
            premiumByRisk.put(entry.getKey().getRiskType(), entry.getValue().multiply(entry.getKey()
                    .getCurrentCoefficient(entry.getValue())).setScale(2, RoundingMode.HALF_EVEN));

            System.out.println("PremiumByRisk: " + premiumByRisk);
        }

        BigDecimal premium = new BigDecimal("0.00");

        for (BigDecimal value : premiumByRisk.values()) {
            premium = premium.add(value);
            System.out.println("premium by risk: " + value);
            System.out.println("premium: " + premium);
        }

        response.setNumber(policy.getNumber());
        response.setStatus(policy.getStatus());
        response.setPremium(premium);

        return response;
    }
}
