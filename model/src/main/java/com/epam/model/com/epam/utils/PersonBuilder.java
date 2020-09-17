package com.epam.model.com.epam.utils;

import com.epam.model.Person;

public class PersonBuilder {
    private String firstName;
    private String lastName;
    private Integer age;
    private Boolean isMarried;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMarried(Boolean married) {
        isMarried = married;
    }

    public Person build() {
        return new Person(firstName, lastName, age, isMarried);
    }
}
