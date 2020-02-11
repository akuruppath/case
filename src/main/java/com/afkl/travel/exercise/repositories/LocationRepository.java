package com.afkl.travel.exercise.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.afkl.travel.exercise.domain.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {

	Collection<Location> findByTypeAndCode(String type, String isoCode);
}
