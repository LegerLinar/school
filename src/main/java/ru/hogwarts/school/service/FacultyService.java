package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

@Service
public class FacultyService{
    private long idIncrementor = 0;
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
//        faculty.setId(++idIncrementor);
        ;
        if (facultyRepository.save(faculty) == null) {
            return faculty;
        }
        return null;
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.getById(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (facultyRepository.save(faculty) == null) {
            return null;
        }
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        return facultyRepository.deleteFacultyById(id);
    }

    public Collection<Faculty> getFacultySetByColor(String color) {
        return facultyRepository.getFacultiesByColor(color);
    }
}
