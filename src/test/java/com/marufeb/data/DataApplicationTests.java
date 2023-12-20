package com.marufeb.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class DataApplicationTests {

    final ApplicationModules modules = ApplicationModules.of(getClass().getPackageName());
    final Documenter documenter = new Documenter(modules);

    @Test
    void contextLoads() {
    }

    @Test
    void verifyArchitecture() {
        modules.verify();
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "documentation", matches = "true", disabledReason = "Disabled by default")
    void generateDocumentation() {
        documenter.writeDocumentation();
    }

}
