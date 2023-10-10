package ru.hogwarts.school.model;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private long id = 0L;
    private String name;
    private int age;




    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
