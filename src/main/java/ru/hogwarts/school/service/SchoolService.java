package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

@Service
public class SchoolService {

    private long idIncrementor = 0;
    private final StudentRepository studentRepository;

    public SchoolService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        if(studentRepository.save(student) == null) {
            return student;
        }
        return null;
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        if (studentRepository.findById(student.getId()) != null) {
            return studentRepository.save(student);
        }
        return null;
    }

    public Student deleteStudent(long id) {
        return studentRepository.deleteStudentById(id);

    }

    public Collection<Student> getStudentSetByAge(int age) {
        return studentRepository.getStudentsByAge(age);
    }

    public Collection<Student> filterByAgeBetween(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public Faculty findFaculty(long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return student.getFaculty();
    }
}
