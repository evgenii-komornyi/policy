package com.evgen.policyApp.repository;

import com.evgen.policyApp.domain.policy.Risk;

import java.util.HashMap;
import java.util.Map;

public class RiskRepository {
    private static Map<String, Risk> allRisks = new HashMap<>();

    public boolean addRisk(Risk risk) {
        if (!allRisks.containsKey(risk.getType())) {
            allRisks.put(risk.getType(), risk);
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Risk> getAllRisks() {
        return allRisks;
    }
}
