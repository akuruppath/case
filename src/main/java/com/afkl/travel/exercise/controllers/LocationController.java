package com.afkl.travel.exercise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afkl.travel.exercise.domain.Location;
import com.afkl.travel.exercise.repositories.LocationRepository;

@RestController
public class LocationController {
	
	private final LocationRepository locationRepository;
	
	@Autowired
	public LocationController(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	@GetMapping(path = "/locations", produces = "application/json")
	public HttpEntity<Iterable<Location>> getAllLocations() {
		return new ResponseEntity<>(locationRepository.findAll(), HttpStatus.OK);
	}
}
