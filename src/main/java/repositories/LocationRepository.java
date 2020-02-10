package repositories;

import org.springframework.data.repository.CrudRepository;

import com.afkl.travel.exercise.domain.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {

}
