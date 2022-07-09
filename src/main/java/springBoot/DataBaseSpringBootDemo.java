package springBoot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import springBoot.springDatabase.PersonJDBCDAO;
import springBoot.springDatabase.entity.Person;

import java.util.Date;

public class DataBaseSpringBootDemo implements CommandLineRunner {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJDBCDAO personJDBCDAO;

    public static void main(String[] args){
        SpringApplication.run(DataBaseSpringBootDemo.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("All persons -> {}", personJDBCDAO.findAll());
        LOGGER.info("Person -> {}", personJDBCDAO.findById(10001));
        LOGGER.info("Delete Person 10002 -> no of rows affected {}", personJDBCDAO.deleteById(10002));
        LOGGER.info("update Person 10001 -> no of rows affected {}",
                personJDBCDAO.update(new Person(10001, "Ranga", "Bartley", new Date())));
        LOGGER.info("insert Person -> no of rows affected {}",
                personJDBCDAO.insert(new Person(10004, "Sara", "Hyderabad", new Date())));
    }
}
