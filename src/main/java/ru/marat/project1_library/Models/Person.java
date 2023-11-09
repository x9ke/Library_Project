package ru.marat.project1_library.Models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public class Person {

    private int personId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 ch")
    private String personName;
    @Min(value = 0, message = "Age should be greater than 0 ")
    @Max(value = 100, message = "Age should be less than 100 ")
    private int personAge;

    public Person() {

    }

    public Person(int personId, String personName, int personAge) {
        this.personId = personId;
        this.personName = personName;
        this.personAge = personAge;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonAge() {
        return personAge;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }
}
