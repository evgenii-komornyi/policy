package com.evgen.policyApp.service;

import com.evgen.policyApp.domain.policy.Risk;
import com.evgen.policyApp.domain.policy.request.ObjectRequest;
import com.evgen.policyApp.domain.policy.request.PolicyRequest;
import com.evgen.policyApp.domain.policy.request.SubObjectRequest;
import com.evgen.policyApp.repository.RiskRepository;
import com.evgen.policyApp.service.validation.RequestValidation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class PremiumCalculatorServiceTest {
    @Mock
    private RiskRepository repository = new RiskRepository();

    private RequestValidation validation = new RequestValidation();

    private PremiumCalculatorService victim = new PremiumCalculatorService(repository, validation);

    @Test
    void shouldReturnPremium() throws IOException {
        Risk fire = new Risk("FIRE", "fire.groovy");
        Risk theft = new Risk("THEFT", "theft.groovy");
        repository.addRisk(fire);
        repository.addRisk(theft);

        SubObjectRequest tv = new SubObjectRequest();
        tv.setName("TV");
        tv.setCost(new BigDecimal("100"));

        List<String> riskTypeTV = new ArrayList<>();
        riskTypeTV.add(fire.getRiskType());
        tv.setRisks(riskTypeTV);

        SubObjectRequest microwave = new SubObjectRequest();
        microwave.setName("Microwave");
        microwave.setCost(new BigDecimal("8"));

        List<String> riskTypeMicrowave = new ArrayList<>();
        riskTypeMicrowave.add(theft.getRiskType());
        microwave.setRisks(riskTypeMicrowave);

        ObjectRequest house = new ObjectRequest();
        house.setName("House");

        List<SubObjectRequest> items = new ArrayList<>();
        items.add(tv);
        items.add(microwave);
        house.setItems(items);

        PolicyRequest request = new PolicyRequest();
        request.setNumber("LV-1025-525588966");
        request.setStatus("registered");

        List<ObjectRequest> objects = new ArrayList<>();
        objects.add(house);
        request.setObjects(objects);

        BigDecimal expectedResult = new BigDecimal("2.28");
        BigDecimal actual = victim.validateAndCalculate(request).getCalculateResponse().getPremium();

        assertEquals(expectedResult, actual);
    }

    private Map<String, Risk> initRisks() {
        RiskRepository repository = new RiskRepository();

        Risk fire = new Risk("FIRE", "fire.groovy");
        Risk theft = new Risk("THEFT", "theft.groovy");

        repository.addRisk(fire);
        repository.addRisk(theft);

        return repository.getAllRisks();
    }
}