package com.automation.stepDefinition;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.How.CSS;
import static org.openqa.selenium.support.How.LINK_TEXT;
import static org.openqa.selenium.support.How.ID;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.configReader.AssessmentProjectConfig;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class TC01_ApplyForNumberPlateStepDefinition extends HtmlElement  {
	
	@FindBy(how = LINK_TEXT, using = "Number plates")
	public WebElement numberPlatesLink;
	
	@FindBy(how = LINK_TEXT, using = "Apply for a number plate")
	public WebElement applyForNumberPlateLink;	
	
	@FindBy(how = CSS, using = "a[href='/services/driving-and-transport']")
	public WebElement drivingAndTranportLink;
	
	@FindBy(how = LINK_TEXT, using = "Find locations")
	public WebElement findLocationsTab;
	
	@FindBy(how = ID, using = "locatorTextSearch")
	public WebElement locationSearchTextbox;
	
	private String expectedPageTitle="Home | Service NSW";
	
	private String expectedDrivingAndTransportPageTitle="Driving and transport | Service NSW";
	
	private String expectedNumberPlatesPageTitle="Number plates | Service NSW";
	
	private String expectedApplyNumPlatePageTitle="Apply for a number plate | Service NSW";	
	
	private String expectedFindLocationsPageTitle="Find a Service NSW location | Service NSW";		
	
	public WebDriver driver;
	
	public static AssessmentProjectConfig assessmentProjectConfig;
	
    static Logger logger = Logger.getLogger(TC01_ApplyForNumberPlateStepDefinition.class);

	
	public  TC01_ApplyForNumberPlateStepDefinition() {
		assessmentProjectConfig=ConfigFactory.create(AssessmentProjectConfig.class);
		System.setProperty("webdriver.chrome.driver", assessmentProjectConfig.getDriverPath());  
	    driver=new ChromeDriver();
        PageFactory.initElements(this.driver, this);
        DOMConfigurator.configure("src/test/resources/config/"+"log4j.xml");
        logger.info("Step completed - TC01_ApplyForNumberPlateStepDefinition");


	}	
	
	@Given("^user is in 'Service NSW Gov' Page$")
    public void userIsOnServiceNSWGovPage() {
	    driver.navigate().to(assessmentProjectConfig.getServiceNSWGOVURL()); 
	}

	@Then("^verify page title$")
	public void verifyPageTile(){
		assertEquals(expectedPageTitle,driver.getTitle());
	}
		
	@When("^user clicks on 'Driving and transport'$")
	public void userClicksOnDrivingAndTransport() {
		drivingAndTranportLink.click();
	}
	
	
	@Then("^verify user is in 'Driving and transport' page$")
	public void verifyPageTitleIsDrivingAndTransport(){
		assertEquals(expectedDrivingAndTransportPageTitle,driver.getTitle());
	}
	
	
	@When("^user clicks on 'Number Plates'$")
	public void userClicksOnNumberPlates() {
		numberPlatesLink.click();
	}
	
	@Then("^verify user is in 'Number plates' page$")
	public void verifyPageTitleIsNumberPlates(){
		assertEquals(expectedNumberPlatesPageTitle,driver.getTitle());
	}
	
	@When("^user clicks on 'Apply for a number plate'$")
	public void userClicksOnApplyForANumberPlate() {
		applyForNumberPlateLink.click();
	}
	
	@Then("^verify user is in 'Apply for a number plate' page$")
	public void verifyPageTitleIsApplyForANumberPlate(){
		assertEquals(expectedApplyNumPlatePageTitle,driver.getTitle());
	}
	
	@When("^user clicks on 'Find Locations' tab$")
	public void userClicksOnFindLocations() {
		findLocationsTab.click();
	}
	
	@Then("^verify user is in 'Find Locations' page$")
	public void verifyPageTitleIsFindLocations(){
		assertEquals(expectedFindLocationsPageTitle,driver.getTitle());
	}
	
	@When("^user searches for suburb (.*)$")
	public void userSearchesLocations(String suburb) {
		locationSearchTextbox.clear();
		locationSearchTextbox.sendKeys(suburb);
		locationSearchTextbox.sendKeys(Keys.ENTER);
	}
	
	@Then("^verify and click on service centre - (.*)$")
	public void verifyAndClickOnServiceCentre(String serviceCentreName){
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(serviceCentreName)));
		WebElement serviceCentre= driver.findElement(By.partialLinkText(serviceCentreName));
		assertTrue(serviceCentre.isDisplayed());
		serviceCentre.click();
        logger.info("Step completed - verifyAndClickOnServiceCentre");

	}
}
