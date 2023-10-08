package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private HashMap<Long, Student> studentMap = new HashMap<>();
    private long idIncrementor = 0;


    public Student createStudent(Student student) {
        student.setId(++idIncrementor);
        if(studentMap.put(idIncrementor, student) == null) {
            return student;
        }
        return null;
    }

    public Student findStudent(long id) {
        return studentMap.get(id);
    }

    public Student updateStudent(Student student) {
        if (studentMap.put(student.getId(), student) == null) {
            return null;
        }
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
