package com.cts.inb.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.cts.inb.cucumber"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
        monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    // Simple TestNG+Cucumber runner
}
