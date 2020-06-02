package com.evgen.policyApp;

import com.evgen.policyApp.domain.policy.*;
import com.evgen.policyApp.repository.RiskRepository;
import com.evgen.policyApp.service.PremiumCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AppTest {
    public static void main(String[] args) {
        Risk fire = new Risk("FIRE", "fire");
        Risk theft = new Risk("THEFT", "theft");

        PolicySubObject tv = new PolicySubObject();
        tv.setName("TV");
        tv.setCostOfTheItem(new BigDecimal("500.0"));

        List<Risk> riskTypeTV = new ArrayList<>();
        riskTypeTV.add(fire);

        tv.setRiskType(riskTypeTV);

        PolicySubObject microwave = new PolicySubObject();
        microwave.setName("Microwave");
        microwave.setCostOfTheItem(new BigDecimal("102.51"));

        List<Risk> riskTypeMicrowave = new ArrayList<>();
        riskTypeMicrowave.add(theft);

        microwave.setRiskType(riskTypeMicrowave);

        PolicyObject house = new PolicyObject();
        house.setName("House");

        List<PolicySubObject> itemsInHouse = new ArrayList<>();
        itemsInHouse.add(tv);
        itemsInHouse.add(microwave);

        house.setSubObjects(itemsInHouse);

        Policy policyOne = new Policy();
        policyOne.setNumber("LV-0206-2569800");
        policyOne.setStatus(PolicyStatus.REGISTERED);

        List<PolicyObject> objects = new ArrayList<>();
        objects.add(house);

        policyOne.setObjects(objects);

        RiskRepository repository = new RiskRepository();
        PremiumCalculator premiumService = new PremiumCalculator(repository);
        premiumService.addRisks(fire);
        premiumService.addRisks(theft);

        System.out.println(premiumService.calculate(policyOne));
    }
}
