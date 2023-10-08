package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private HashMap<Long, Faculty> facultyMap = new HashMap<>();
    private long idIncrementor = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++idIncrementor);
        if (facultyMap.put(idIncrementor, faculty) == null) {
            return faculty;
        }
        return null;
    }

    public Faculty findFaculty(long id) {
        return facultyMap.get(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (facultyMap.put(faculty.getId(), faculty) == null) {
            return null;
        }
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        return facultyMap.remove(id);
    }

    public Collection<Faculty> getFacultySetByColor(String color) {
        return facultyMap.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toSet());
    }
}
