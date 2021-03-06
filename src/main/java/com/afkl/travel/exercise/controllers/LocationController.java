package com.afkl.travel.exercise.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afkl.travel.exercise.domain.Location;
import com.afkl.travel.exercise.repositories.LocationRepository;

@RestController
@RequestMapping("${service.locations.url}")
public class LocationController {

	
	private final LocationRepository locationRepository;
	private final List<String> isoCountries;

	@Autowired
	public LocationController(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
		isoCountries = Arrays.asList(Locale.getISOCountries());
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Iterable<Location>> getAllLocations() {
		return new ResponseEntity<>(locationRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "${service.locations.urlparams}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpEntity<Iterable<Location>> getLocationByTypeAndCode(@PathVariable("type") String type,
			@PathVariable("code") String isoCode) {

		Assert.isTrue(StringUtils.isNotBlank(type), "Location type cannot be blank.");
		Assert.isTrue(StringUtils.isNotBlank(isoCode), "Location code cannot be blank.");
		String iso = isoCode.toUpperCase(Locale.US);
		Assert.isTrue(isoCountries.contains(iso), "Invalid Location code.");
		return new ResponseEntity<>(locationRepository.findByTypeAndCode(type, iso),
				HttpStatus.OK);
	}
}
