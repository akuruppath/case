package com.afkl.travel.exercise.repositories;

import org.springframework.data.repository.CrudRepository;

import com.afkl.travel.exercise.domain.Translation;

public interface TranslationRepository extends CrudRepository<Translation, Integer> {

}
