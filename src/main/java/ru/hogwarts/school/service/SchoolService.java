package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Service
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

    public Collection<Student> getStudentSetByAge(int age) {
        return studentMap.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toSet());
    }
}
