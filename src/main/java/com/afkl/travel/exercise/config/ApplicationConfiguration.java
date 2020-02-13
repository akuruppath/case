package com.afkl.travel.exercise.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "service")
public class ApplicationConfiguration {

	private Map<String, String> locations;
	private Map<String, String> actuator;
	
	public Map<String, String> getLocations() {
		return locations;
	}
	public Map<String, String> getActuator() {
		return actuator;
	}
	public void setLocations(Map<String, String> locations) {
		this.locations = locations;
	}
	public void setActuator(Map<String, String> actuator) {
		this.actuator = actuator;
	}
}
