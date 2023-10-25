package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

@Service
public class FacultyService{
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        if (facultyRepository.save(faculty) == null) {
            return faculty;
        }
        return null;
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (facultyRepository.findById(faculty.getId()) != null) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public Faculty deleteFaculty(long id) {
        return facultyRepository.deleteFacultyById(id);
    }

    public Collection<Faculty> getFacultySetByColor(String color) {
        return facultyRepository.getFacultiesByColor(color);
    }


    public Collection<Faculty> filterByNameOrColor(String name, String color) {
        return facultyRepository.findAllByNameOrColorIgnoreCase(name, color);
    }

    public Collection<Student> findStudentsOfFaculty(long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found"));
        return faculty.getStudents();
    }
}
