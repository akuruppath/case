package com.afkl.travel.exercise.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.afkl.travel.exercise.domain.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {

}
