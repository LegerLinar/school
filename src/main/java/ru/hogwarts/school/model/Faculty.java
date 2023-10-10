package ru.hogwarts.school.model;

import javax.persistence.*;

@Entity

public class Faculty {
    @Id
    @GeneratedValue
    private long id = 0L;
    private String name;
    private String color;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
