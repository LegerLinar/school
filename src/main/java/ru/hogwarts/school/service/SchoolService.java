package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Service
public class SchoolService {

    private long idIncrementor = 0;
    private final StudentRepository studentRepository;

    public SchoolService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        student.setId(++idIncrementor);
        if(studentRepository.save(student) == null) {
            return student;
        }
        return null;
    }

    public Student findStudent(long id) {
        return studentRepository.getStudentById(id);
    }

    public Student updateStudent(Student student) {
        if (studentRepository.save(student) == null) {
            return null;
        }
        return student;
    }

    public Student deleteStudent(long id) {
        return studentRepository.deleteStudentById(id);

    }

    public Collection<Student> getStudentSetByAge(int age) {
        return studentRepository.getStudentsByAge(age);
    }
}
