package springBoot.springdata;

import org.springframework.stereotype.Repository;
import springBoot.springdata.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonJpaRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Person findById(int id){
       return entityManager.find(Person.class, id);
    }
}
