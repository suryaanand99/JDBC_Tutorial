package springBoot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springBoot.jdbc.PersonJDBCDAO;

@SpringBootApplication
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
    }
}
