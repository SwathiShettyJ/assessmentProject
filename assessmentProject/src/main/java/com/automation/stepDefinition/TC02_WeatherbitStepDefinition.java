package com.automation.stepDefinition;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.How.CSS;
import static org.openqa.selenium.support.How.LINK_TEXT;

import java.util.Iterator;
import java.util.Map;

import static org.openqa.selenium.support.How.ID;



import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.configReader.AssessmentProjectConfig;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class TC02_WeatherbitStepDefinition  {

		public static AssessmentProjectConfig assessmentProjectConfig;
		
		private String responseMsg;

	    public  TC02_WeatherbitStepDefinition() {
			assessmentProjectConfig=ConfigFactory.create(AssessmentProjectConfig.class);
			System.setProperty("webdriver.chrome.driver", assessmentProjectConfig.getDriverPath()); 
			   System.out.println(assessmentProjectConfig.getBaseurl());

		}	
	 
	    
	   @When("^user passes the (.*) & (.*) values in weatherbit current weather get url$")
	   public void getFromCurrentWeatherUrl(String lat, String lon){
		   String currentWeatherurl= assessmentProjectConfig.getBaseurl()+ assessmentProjectConfig.getCurrentWeather()
		   							 +"?lat="+lat+"&lon="+lon;
		   System.out.println(currentWeatherurl);
		   responseMsg=restAssuredGet(currentWeatherurl);	  	   
	   }
	   
	   
	   @When("^Then verify statecode is (.*)$")
	   public void verifyStateCode(){
		   JsonParser parse = new JsonParser();
		   JsonObject jobj = (JsonObject)parse.parse(responseMsg);
		   JsonArray jsonarr_1 = (JsonArray) jobj.get("data");
		   System.out.println(""+ ((JsonObject)jsonarr_1.get(0)).get("state_code"));	
		   
	   }
	   
	   @When("^user passes the (.*) in weatherbit forcast get url$")
	   public void getFromForcastUrl(String postalcode){
		   //28546
		   String forcastUrl= assessmentProjectConfig.getBaseurl()+ assessmentProjectConfig.getForecastDaily()
		   							 +"?postal_code="+postalcode;
		   System.out.println(forcastUrl);
		   responseMsg=restAssuredGet(forcastUrl);	  	   
	   }
	   
	   
	   @When("^Then verify statecode is (.*)$")
	   public void verifyTimeStampAndWeather(){
		   JsonParser parse = new JsonParser();
		   JsonObject jobj = (JsonObject)parse.parse(responseMsg);
		   JsonArray jsonarr_1 = (JsonArray) jobj.get("data");
		   System.out.println(""+ ((JsonObject)jsonarr_1.get(0)).get("state_code"));	
		   
	   }

	   
	   private String restAssuredGet(String requestUrl) {
		   return RestAssured.given()
				   .param("key", assessmentProjectConfig.getApitoken())
				   .get(requestUrl)
		           .then().log().body()
		           .spec(new ResponseSpecBuilder().expectStatusCode(200).build())
		           .extract()
	           .body().asString();
		   
	   }
	   public static void main(String [] args) {
		   assessmentProjectConfig=ConfigFactory.create(AssessmentProjectConfig.class);
		   System.out.println(assessmentProjectConfig.getBaseurl());
		   String currentWeatherurl= assessmentProjectConfig.getBaseurl()+ assessmentProjectConfig.getCurrentWeather() + "?lat=38&lon=-78.25";
		   System.out.println(currentWeatherurl);

		   String responseMsg =RestAssured.given()
				   .param("key", assessmentProjectConfig.getApitoken())
				   .get(currentWeatherurl)
		           .then().log().body()
		           .spec(new ResponseSpecBuilder().expectStatusCode(200).build())
		           .extract()
		           .body().asString();
		   
//		   String responseMsg =RestAssured.given().param("lat", "38")
//				   .param("lon", "-78.25")
//				   .param("key", assessmentProjectConfig.getApitoken())
//				   .get(currentWeatherurl)
//		           .then().log().body()
//		           .spec(new ResponseSpecBuilder().expectStatusCode(200).build())
//		           .extract()
//		           .body().asString();
		   
		   JsonParser parse = new JsonParser();
		   JsonObject jobj = (JsonObject)parse.parse(responseMsg);
		   JsonArray jsonarr_1 = (JsonArray) jobj.get("data");
		   System.out.println(""+ ((JsonObject)jsonarr_1.get(0)).get("state_code"));	   

	   }
}
