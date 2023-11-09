package ru.marat.project1_library.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.marat.project1_library.Dao.PersonDAO;
import ru.marat.project1_library.Models.Person;


@Component
public class PersonValidator implements Validator {


    private PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        //посмотреть если ли человек с таким же мылом
        if (personDAO.show(person.getPersonName()).isPresent()){

            errors.rejectValue("personName", "", "This name is already taken");


        }

    }
}
