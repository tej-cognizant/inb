package com.cts.inb.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SmokeSteps {

    @Given("the framework is ready")
    public void the_framework_is_ready() {
        // placeholder setup check
    }

    @When("I run a cucumber smoke")
    public void i_run_a_cucumber_smoke() {
        // placeholder action
    }

    @Then("it should pass")
    public void it_should_pass() {
        // basic assertion to keep scenario green
        Assert.assertTrue(true, "Smoke scenario should pass");
    }
}
