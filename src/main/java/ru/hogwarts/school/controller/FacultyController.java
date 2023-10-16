package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty tempFac = facultyService.createFaculty(faculty);
        if (!verifierNotNull(tempFac)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(tempFac);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable("id") long id) {
        Faculty tempFaculty = facultyService.findFaculty(id);
        if (!verifierNotNull(tempFaculty)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tempFaculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty tempFaculty = facultyService.updateFaculty(faculty);
        if (!verifierNotNull(tempFaculty)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(tempFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable("id") long id) {
        Faculty tempFaculty = facultyService.deleteFaculty(id);
        if (!verifierNotNull(tempFaculty)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tempFaculty);
    }

    @GetMapping("/get")
    public ResponseEntity<Collection<Faculty>> getFacultySetByColor(@RequestParam("color")
                                                                    String color) {
        Collection<Faculty> tempCollection = facultyService.getFacultySetByColor(color);
        return ResponseEntity.ok(tempCollection);
    }

    @GetMapping("/filterByNameOrColor")
    public Collection<Faculty> filterByNameOrColor(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String color) {
        return facultyService.filterByNameOrColor(name, color);
    }

    @GetMapping("/{id}}/students")
    public Collection<Student> findStudentsOfFaculty(@PathVariable("id") long facultyId) {
        return facultyService.findStudentsOfFaculty(facultyId);
    }

    private boolean verifierNotNull(Faculty faculty) {
        return faculty != null;
    }
}
