package com.evgen.policyApp.repository;

import com.evgen.policyApp.domain.policy.Risk;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RiskRepository {
    private static Map<String, Risk> allRisks = new HashMap<>();

    public boolean addRisk(Risk risk) {
        if (!allRisks.containsKey(risk.getRiskType())) {
            allRisks.put(risk.getRiskType(), risk);
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Risk> getAllRisks() {
        return allRisks;
    }
}
