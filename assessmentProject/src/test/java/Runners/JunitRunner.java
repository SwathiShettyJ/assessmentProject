package Runners;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.apache.log4j.xml.DOMConfigurator;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features   = "src/test/resources/features",
    monochrome = true,
    glue       = "com/automation/stepDefinition",
    tags	   = "@TC01_ApplyForNumberPlates,@TC02_WeatherbitAPI",
    plugin 	= { "pretty", "html:target/cucumber-reports" }
)
public class JunitRunner {
	
}
