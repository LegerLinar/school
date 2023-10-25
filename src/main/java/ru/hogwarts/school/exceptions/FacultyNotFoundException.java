package ru.hogwarts.school.exceptions;

public class FacultyNotFoundException extends IllegalArgumentException {
    public FacultyNotFoundException(String msg) {
        super(msg);
    }
}
