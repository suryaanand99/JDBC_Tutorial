package springBoot.springDatabase;

import org.springframework.stereotype.Repository;
import springBoot.springDatabase.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Person findById(int id){
       return entityManager.find(Person.class, id);
    }

    public Person update(Person person) {
        return entityManager.merge(person);
    }

    public Person insert(Person person){
        return entityManager.merge(person);
    }

    public void deleteById(int id) {
        entityManager.remove(findById(id));
    }

    public List<Person> findAll() {
        final TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find-all-person", Person.class);
        return namedQuery.getResultList();
    }
}
