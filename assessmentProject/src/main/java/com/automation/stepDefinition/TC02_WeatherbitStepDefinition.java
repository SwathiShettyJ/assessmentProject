package com.automation.stepDefinition;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import com.automation.configReader.AssessmentProjectConfig;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;

public class TC02_WeatherbitStepDefinition  {

	public static AssessmentProjectConfig assessmentProjectConfig;

	private String responseMsg;

	static Logger logger = Logger.getLogger(TC02_WeatherbitStepDefinition.class);

	public  TC02_WeatherbitStepDefinition() {
		assessmentProjectConfig=ConfigFactory.create(AssessmentProjectConfig.class);
		System.setProperty("webdriver.chrome.driver", assessmentProjectConfig.getDriverPath()); 
		System.out.println(assessmentProjectConfig.getBaseurl());
		logger.info("Step completed - TC01_ApplyForNumberPlateStepDefinition");

	}	


	@When("^user passes the (.*) & (.*) values in weatherbit current weather get url$")
	public void getFromCurrentWeatherUrl(String lat, String lon){
		String currentWeatherurl= assessmentProjectConfig.getBaseurl()+ assessmentProjectConfig.getCurrentWeather()
		+"?lat="+lat+"&lon="+lon;
		System.out.println(currentWeatherurl);
		responseMsg=restAssuredGet(currentWeatherurl);	
		logger.info("==============================================");
		logger.info(responseMsg);
		logger.info("==============================================");
	}


	@Then("^verify statecode is (.*)$")
	public void verifyStateCode(String state_code){
		JsonParser parse = new JsonParser();
		JsonObject jobj = (JsonObject)parse.parse(responseMsg);
		JsonArray jsonarr_1 = (JsonArray) jobj.get("data");
		System.out.println(""+ ((JsonObject)jsonarr_1.get(0)).get("state_code"));
		logger.info("==============================================");
		logger.info("State Code : "+ ((JsonObject)jsonarr_1.get(0)).get("state_code"));
		logger.info("==============================================");
	}

	@When("^user passes the (.*) in weatherbit forcast get url$")
	public void getFromForcastUrl(String postalcode){
		String forcastUrl= assessmentProjectConfig.getBaseurl()+ assessmentProjectConfig.getForecastDaily()
		+"?postal_code="+postalcode;
		System.out.println(forcastUrl);
		responseMsg=restAssuredGet(forcastUrl);	 
		logger.info("==============================================");
		logger.info("responseMsg");
		logger.info("==============================================");

	}


	@When("^verify TimeStamp and Weather$")
	public void verifyTimeStampAndWeather(){
		try {
			ObjectMapper jsonMapper = new ObjectMapper();
			final ObjectNode root = (ObjectNode)jsonMapper.readTree(responseMsg);
			logger.info("==============================================");
			for(int i=0;i<root.get("data").size();i++) {
				JsonNode time = root.get("data").get(i).get("datetime");
				System.out.println("data-->"+time);
				JsonNode weather=root.get("data").get(i).get("weather");
				System.out.println("weather -->"+weather);
				logger.info("{timestamp_utc, weather} : {"+ time+" ,"+weather +"}" );
			}
			logger.info("==============================================");

		}catch(Exception e) {
			e.printStackTrace();
		}
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

}
