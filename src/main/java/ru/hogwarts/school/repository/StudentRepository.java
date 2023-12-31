package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student deleteStudentById(long id);
    Collection<Student> getStudentsByAge(int age);

    Collection<Student> findAllByAgeBetween(int min, int max);

}
