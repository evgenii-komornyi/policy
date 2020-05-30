package com.evgen.policyApp;

import com.evgen.policyApp.domain.policy.Policy;
import com.evgen.policyApp.domain.policy.PolicyObject;
import com.evgen.policyApp.domain.policy.PolicyStatus;
import com.evgen.policyApp.domain.policy.PolicySubObject;
import com.evgen.policyApp.domain.policy.riskType.Risk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AppTest {
    public static void main(String[] args) {
        Risk fire = new Risk("FIRE", new BigDecimal("0.014"), new BigDecimal("0.024"), new BigDecimal("100"));
        Risk theft = new Risk("THEFT", new BigDecimal("0.11"), new BigDecimal("0.05"), new BigDecimal("15"));

        PolicySubObject tv = new PolicySubObject();
        tv.setName("TV");
        tv.setSumInsured(new BigDecimal("600.0"));

        List<Risk> riskTypeTV = new ArrayList<>();
        riskTypeTV.add(fire);
        riskTypeTV.add(theft);

        tv.setRiskType(riskTypeTV);

        PolicySubObject microwave = new PolicySubObject();
        microwave.setName("Microwave");
        microwave.setSumInsured(new BigDecimal("50.0"));

        List<Risk> riskTypeMicrowave = new ArrayList<>();
        riskTypeMicrowave.add(theft);

        microwave.setRiskType(riskTypeTV);

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

        System.out.println(policyOne);
    }
}
