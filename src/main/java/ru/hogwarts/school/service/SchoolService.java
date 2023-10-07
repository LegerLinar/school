package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.HashMap;

public class SchoolService {

    private HashMap<Long, Student> studentMap = new HashMap<>();
    private long idIncrementor = 0;


    public Student addStudent(Student student) {
        student.setId(++idIncrementor);
        studentMap.put(idIncrementor, student);
        return student;
    }

    public Student findStudent(long id) {
        return studentMap.get(id);
    }

    public Student updateStudent(Student student) {
        studentMap.put(student.getId(),student);
        return student;
    }

    public Student deleteStudent(long id) {
        return studentMap.remove(id);

    }
}
