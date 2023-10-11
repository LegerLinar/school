package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Faculty save(Faculty faculty);

    Faculty getById(long id);

    Faculty deleteFacultyById(long id);

    Collection<Faculty> getFacultiesByColor(String color);
}
