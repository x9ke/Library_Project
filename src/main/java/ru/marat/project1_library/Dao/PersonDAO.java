package ru.marat.project1_library.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.marat.project1_library.Models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<Person> index(){

        return  jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));

    }

    public Optional<Person> show(String personName){
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_name=?", new Object[] {personName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person show(int personId) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {

        jdbcTemplate.update("INSERT INTO Person(person_name, person_age) VALUES(?, ?)", person.getPersonName(), person.getPersonAge());

    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET person_name=?,person_age=? WHERE person_id=?", updatePerson.getPersonName(), updatePerson.getPersonAge(), id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);

    }


}
