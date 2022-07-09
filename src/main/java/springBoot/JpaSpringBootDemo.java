package springBoot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springBoot.springDatabase.PersonJpaRepository;
import springBoot.springDatabase.entity.Person;

import java.util.Date;

@SpringBootApplication
public class JpaSpringBootDemo implements CommandLineRunner {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJpaRepository personJpaDAO;

    public static void main(String[] args){
        SpringApplication.run(JpaSpringBootDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Person -> {}", personJpaDAO.findById(10001));
//        LOGGER.info("All persons -> {}", personJpaDAO.findAll());
//        LOGGER.info("Delete Person 10002 -> no of rows affected {}", personJpaDAO.deleteById(10002));
        LOGGER.info("update Person 10001 -> no of rows affected {}",
                personJpaDAO.update(new Person(10001, "Ranga", "Bartley", new Date())));
        LOGGER.info("insert Person -> no of rows affected {}",
                personJpaDAO.insert(new Person("Sara", "Hyderabad", new Date())));
    }
}
