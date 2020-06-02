package com.evgen.policyApp.service;

import com.evgen.policyApp.domain.policy.Policy;
import com.evgen.policyApp.domain.policy.PolicyObject;
import com.evgen.policyApp.domain.policy.PolicySubObject;
import com.evgen.policyApp.domain.policy.Risk;
import com.evgen.policyApp.repository.RiskRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class PremiumCalculator {
    private RiskRepository repository;

    public PremiumCalculator(RiskRepository repository) {
        this.repository = repository;
    }

    public boolean addRisks(Risk riskType) {
        return repository.addRisk(riskType);
    }

    public BigDecimal calculate(Policy policy) {
        Map<String, Risk> allRisks = repository.getAllRisks();

        Map<Risk, BigDecimal> amountByRisk = new HashMap<>();

        for (PolicyObject object : policy.getObjects()) {
            System.out.println("Object: " + object.getName());
            for (PolicySubObject item : object.getSubObjects()) {
                System.out.println("Item: " + item.getName());
                System.out.println("Cost: " + item.getCostOfTheItem());
                for (Risk risk : item.getRiskType()) {
                    System.out.println("Risk: " + risk.getType());

                    amountByRisk.computeIfPresent(risk, (key, val) -> val.add(item.getCostOfTheItem()));
                    amountByRisk.putIfAbsent(risk, item.getCostOfTheItem());
                }
            }
        }

        Map<String, BigDecimal> premiumByRisk = new HashMap<>();

        for (Map.Entry<Risk, BigDecimal> entry : amountByRisk.entrySet()) {
            premiumByRisk.put(entry.getKey().getType(), entry.getValue().multiply(entry.getKey()
                    .getCurrentCoefficient(entry.getValue())).setScale(2, RoundingMode.HALF_EVEN));
        }

        System.out.println(premiumByRisk);

        BigDecimal premium = new BigDecimal("0.00");

        for (BigDecimal value : premiumByRisk.values()) {
            premium = premium.add(value);
        }
        return premium;
    }
}
