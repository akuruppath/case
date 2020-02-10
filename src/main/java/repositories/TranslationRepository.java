package repositories;

import org.springframework.data.repository.CrudRepository;

import com.afkl.travel.exercise.domain.Translation;

public interface TranslationRepository extends CrudRepository<Translation, Integer> {

}
