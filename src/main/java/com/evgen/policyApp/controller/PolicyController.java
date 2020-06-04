package com.evgen.policyApp.controller;

import com.evgen.policyApp.domain.policy.Policy;
import com.evgen.policyApp.domain.policy.PolicyObject;
import com.evgen.policyApp.domain.policy.PolicySubObject;
import com.evgen.policyApp.domain.policy.Risk;
import com.evgen.policyApp.domain.policy.request.ObjectRequest;
import com.evgen.policyApp.domain.policy.request.PolicyRequest;
import com.evgen.policyApp.domain.policy.request.SubObjectRequest;
import com.evgen.policyApp.domain.policy.response.CalculateResponse;
import com.evgen.policyApp.dto.PolicyDTO;
import com.evgen.policyApp.repository.RiskRepository;
import com.evgen.policyApp.service.PremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@EnableWebMvc
@CrossOrigin
public class PolicyController {
    @Autowired
    private final PremiumCalculator service;
    @Autowired
    private final RiskRepository riskRepository;

    public PolicyController(PremiumCalculator service, RiskRepository riskRepository) {
        this.service = service;
        this.riskRepository = riskRepository;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<PolicyDTO> createPolicy(@RequestBody PolicyRequest request) throws IOException {
        List<String> errorsList = validate(request);

        PolicyDTO responseJson = convertToDTO(service.calculate(convertToObject(request)), errorsList);

        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

    private Policy convertToObject(PolicyRequest request) {
        Policy policy = new Policy();
        PolicyObject object = new PolicyObject();

        List<PolicyObject> objects = new ArrayList<>();

        for (ObjectRequest requestObject : request.getObjects()) {
            object.setObjectName(requestObject.getName());
            List<PolicySubObject> items = new ArrayList<>();

            for (SubObjectRequest requestItem : requestObject.getItems()) {
                PolicySubObject item = new PolicySubObject();
                List<Risk> riskList = new ArrayList<>();
                List<String> riskWithoutDuplicate = removeDuplicate(requestItem.getRisks());

                for (String risk : riskWithoutDuplicate) {
                    item.setSubObjectName(requestItem.getName());
                    item.setCostOfTheItem(requestItem.getCost());

                    for (Map.Entry<String, Risk> s : riskRepository.getAllRisks().entrySet()) {
                        if (risk.contains(s.getKey())) {
                            riskList.add(s.getValue());
                            item.setRiskType(riskList);
                        }
                    }

                    items.add(item);
                }

                object.setSubObjects(items);
            }

            objects.add(object);
        }

        policy.setObjects(objects);
        policy.setStatus(request.getStatus());
        policy.setNumber(request.getNumber());

        return policy;
    }

    private List<String> validate(PolicyRequest request) {
        List<String> errors = new ArrayList<>();
        for (ObjectRequest object : request.getObjects()) {
            for (SubObjectRequest item : object.getItems()) {
                if (item.getRisks().isEmpty()) {
                    if (!errors.contains("Risk is empty")) {
                        errors.add("Risk is empty");
                    }
                }
            }
        }

        return errors;
    }

    private PolicyDTO convertToDTO(CalculateResponse policy, List<String> errors) {
        PolicyDTO dto = new PolicyDTO();
        dto.setNumber(policy.getNumber());
        dto.setStatus(policy.getStatus());
        dto.setPremium(policy.getPremium());
        dto.setErrors(errors);

        return dto;
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

}
