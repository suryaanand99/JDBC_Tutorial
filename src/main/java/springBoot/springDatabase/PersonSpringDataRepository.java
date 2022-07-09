package springBoot.springDatabase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springBoot.springDatabase.entity.Person;

@Repository
public interface PersonSpringDataRepository extends JpaRepository<Person, Integer> {
}
