package ru.hogwarts.school.model;

public class Student {
    private Long id = 0L;
    private String name;
    private int age;
    public Student(String name, int age) {
        ++id;
        this.name = name;
        this.age = age;
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
