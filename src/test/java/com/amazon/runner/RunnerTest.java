package com.amazon.runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.amazon.dataProviders.JsonDataReader;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(features = "classpath:features", tags = { "@album" }, glue = {
		"com.amazon.steps","com.amazon.api.steps" }, plugin = {
				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" }, monochrome = true)

public class RunnerTest {

	@BeforeClass
	public static void setUp() throws Exception {
		JsonDataReader.registerEnvironment("QA");
		JsonDataReader.initializeJSON();
	}

	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", System.getProperty("os.name"));
		Reporter.setTestRunnerOutput("Sample test runner output message");
	}
}
