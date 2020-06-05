package com.evgen.policyApp.controller;

import com.evgen.policyApp.domain.policy.*;
import com.evgen.policyApp.domain.policy.request.ObjectRequest;
import com.evgen.policyApp.domain.policy.request.PolicyRequest;
import com.evgen.policyApp.domain.policy.request.SubObjectRequest;
import com.evgen.policyApp.domain.policy.response.CalculateResponse;
import com.evgen.policyApp.dto.ErrorsDTO;
import com.evgen.policyApp.dto.PolicyDTO;
import com.evgen.policyApp.dto.ResponseDTO;
import com.evgen.policyApp.repository.RiskRepository;
import com.evgen.policyApp.service.PremiumCalculator;
import com.evgen.policyApp.service.validation.RequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.*;

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
    public ResponseEntity<ResponseDTO> createPolicy(@RequestBody PolicyRequest request) throws IOException {
        RequestValidation validation = new RequestValidation();

        HashSet<String> errorsList = validation.validateRequest(request);
        ResponseDTO response = new ResponseDTO();
        try {
            response.setPolicy(convertToDTO(service.calculate(convertToObject(request))));
        } catch (NullPointerException e) {
            e.getStackTrace();
            if (!errorsList.isEmpty()) {
                response.setErrors(convertErrorsToDTO(errorsList));
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ErrorsDTO convertErrorsToDTO(HashSet<String> errors) {
        ErrorsDTO errorsDTO = new  ErrorsDTO();
        errorsDTO.setErrors(errors);

        return errorsDTO;
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
        policy.setStatus(request.getStatus().toUpperCase());
        policy.setNumber(request.getNumber());

        return policy;
    }

    private PolicyDTO convertToDTO(CalculateResponse policy) {
        PolicyDTO dto = new PolicyDTO();
        dto.setNumber(policy.getNumber());
        dto.setStatus(policy.getStatus());
        dto.setPremium(policy.getPremium());

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
