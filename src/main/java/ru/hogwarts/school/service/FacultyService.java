package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;

public class FacultyService {
    private HashMap<Long, Faculty> facultyMap = new HashMap<>();
    private long idIncrementor = 0;

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++idIncrementor);
        facultyMap.put(idIncrementor, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facultyMap.get(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        return facultyMap.remove(id);
    }
}
