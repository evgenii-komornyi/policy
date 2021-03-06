package com.evgen.policyApp.controller;

import com.evgen.policyApp.domain.policy.request.PolicyRequest;
import com.evgen.policyApp.domain.policy.response.ValidateAndCalculateResponse;
import com.evgen.policyApp.dto.PolicyDTO;
import com.evgen.policyApp.dto.ResponseDTO;
import com.evgen.policyApp.dto.SumInsuredByRiskTypeDTO;
import com.evgen.policyApp.repository.RiskRepository;
import com.evgen.policyApp.service.PremiumCalculatorService;
import com.evgen.policyApp.service.validation.RequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@EnableWebMvc
@CrossOrigin
public class PolicyController {
    @Autowired
    private final PremiumCalculatorService service;
    @Autowired
    private final RiskRepository riskRepository;
    @Autowired
    private final RequestValidation validation;

    public PolicyController(PremiumCalculatorService service, RiskRepository riskRepository, RequestValidation validation) {
        this.service = service;
        this.riskRepository = riskRepository;
        this.validation = validation;
    }

    @PostMapping(value = "/calculate")
    public ResponseEntity<ResponseDTO> createPolicy(@RequestBody PolicyRequest request) {
        ResponseDTO response = new ResponseDTO();

        try {
            response.setPolicy(convertToDTO(service.validateAndCalculate(request)));
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private PolicyDTO convertToDTO(ValidateAndCalculateResponse response) {
        PolicyDTO dto = new PolicyDTO();
        dto.setNumber(response.getNumber());
        dto.setStatus(response.getStatus());
        if (response.getCalculateResponse() != null) {
            dto.setPremium(response.getCalculateResponse().getPremium());

            List<SumInsuredByRiskTypeDTO> sumInsured = new ArrayList<>();

            for (Map.Entry<String, BigDecimal> sumByRisk: response.getCalculateResponse().getAmountByRisk().entrySet()) {
                SumInsuredByRiskTypeDTO sum = new SumInsuredByRiskTypeDTO();

                sum.setRiskType(sumByRisk.getKey());
                sum.setSumInsured(sumByRisk.getValue());

                sumInsured.add(sum);
            }

            dto.setSumInsured(sumInsured);
        }

        if (response.getErrors() != null && !response.getErrors().isEmpty()) {
            dto.setErrors(response.getErrors());
        }

        return dto;
    }
}
