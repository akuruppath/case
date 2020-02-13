package com.afkl.travel.exercise.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.afkl.travel.exercise.config.ApplicationConfiguration;
import com.afkl.travel.exercise.rest.template.RestTemplateFactory;
import com.afkl.travel.exercise.utils.constants.Constants;

@RestController
@RequestMapping("/resttemplate")
public class RestTemplateController {

	private final RestTemplate restTemplate;
	private final Map<String, String> locationsMap;

	@Autowired
	public RestTemplateController(RestTemplateFactory restTemplateFactory, ApplicationConfiguration appConfig) {
		this.restTemplate = restTemplateFactory.getObject();
		this.locationsMap = appConfig.getLocations();
	}

	@GetMapping(produces = Constants.MIME_TYPE)
	public ResponseEntity<String> getUSLocation	() {
		String parametrizedUrl = "http://localhost:8080/" + locationsMap.get(Constants.URL) + "/country/US";
		return restTemplate.exchange(parametrizedUrl, HttpMethod.GET, null, String.class);
	}
}
