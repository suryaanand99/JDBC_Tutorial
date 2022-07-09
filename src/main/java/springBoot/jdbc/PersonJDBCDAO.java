package springBoot.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springBoot.jdbc.entity.Person;

import java.util.List;

@Repository
public class PersonJDBCDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<Person>(Person.class));
    }

    public Person findById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from person where id=?", new Object[]{id},
                new BeanPropertyRowMapper<Person>(Person.class));
    }
}
