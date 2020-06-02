package com.evgen.policyApp.domain.policy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Objects;

public class Risk {
    private String type;
    private String fileName;
    private String pathToScripts = "./src/main/resources/groovyScripts/";

    public Risk(String type, String fileName) {
        this.type = type;
        this.fileName = fileName;
    }

    public BigDecimal getCurrentCoefficient(BigDecimal amount) {
        Binding binding = new Binding();
        binding.setVariable("sum", amount);

        GroovyShell shell = new GroovyShell(binding);

        Script script = shell.parse(getScript(pathToScripts + fileName + ".groovy"));

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
                "type='" + type + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Risk risk = (Risk) o;
        return Objects.equals(type, risk.type) &&
                Objects.equals(fileName, risk.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, fileName);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
