package com.evgen.policyApp;

import com.evgen.policyApp.domain.policy.Risk;
import com.evgen.policyApp.repository.RiskRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Map;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        initRisks();
    }

    private static Map<String, Risk> initRisks() {
        RiskRepository repository = new RiskRepository();

        Risk fire = new Risk("FIRE", "fire.groovy");
        Risk theft = new Risk("THEFT", "theft.groovy");

        repository.addRisk(fire);
        repository.addRisk(theft);

        return repository.getAllRisks();
    }
}
