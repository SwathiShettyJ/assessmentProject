package com.automation.configReader;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.DefaultValue;
import org.aeonbits.owner.Config.Key;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:config/assessmentProject.properties" })
public interface AssessmentProjectConfig extends Config {

	@Key("serviceNSWGOV.url")
	@DefaultValue("https://www.service.nsw.gov.au/")
	String getServiceNSWGOVURL();
	
	@Key("driverPath")
	@DefaultValue("/Users/swathishetty/Documents/Swathi/Personal/Assessment/assessmentProject/assessmentProject/chromedriver")
	String getDriverPath();
	
	
	@Key("weatherbit.baseurl")
	@DefaultValue("http://api.weatherbit.io/v2.0/")
	String getBaseurl();
	
	@Key("weatherbit.apitoken")
	@DefaultValue("1cd51bfd2f51498d9db5d613ff68eb82")
	String getApitoken();
	
	@Key("weatherbit.currentWeather")
	@DefaultValue("current")
	String getCurrentWeather();
	
	@Key("weatherbit.forecastDaily")
	@DefaultValue("forecast/daily")
	String getForecastDaily();
	

}
