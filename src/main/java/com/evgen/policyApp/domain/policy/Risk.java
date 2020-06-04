package com.evgen.policyApp.domain.policy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Properties;

public class Risk {
    private String riskType;
    private String fileName;

    public Risk(String riskType, String fileName) {
        this.riskType = riskType;
        this.fileName = fileName;
    }

    public BigDecimal getCurrentCoefficient(BigDecimal amount) throws IOException {
        Binding binding = new Binding();
        binding.setVariable("sum", amount);

        GroovyShell shell = new GroovyShell(binding);

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "application.properties";
        Properties props = new Properties();
        props.load(new FileInputStream(appConfigPath));
        String location = props.getProperty("scriptsLocation");

        Script script = shell.parse(getScript(rootPath + location + fileName));

        return (BigDecimal) script.run();
    }

    private static InputStreamReader getScript(String scriptPath) {
        try {
            return new InputStreamReader(new FileInputStream(scriptPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toString() {
        return "Risk{" +
                "type='" + riskType + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Risk risk = (Risk) o;
        return Objects.equals(riskType, risk.riskType) &&
                Objects.equals(fileName, risk.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riskType, fileName);
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
