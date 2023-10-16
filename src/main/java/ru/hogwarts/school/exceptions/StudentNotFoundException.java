package ru.hogwarts.school.exceptions;

public class StudentNotFoundException extends IllegalArgumentException{
    public StudentNotFoundException(String msg) {
        super(msg);
    }
}
