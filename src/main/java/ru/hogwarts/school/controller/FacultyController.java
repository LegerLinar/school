package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        Faculty tempFac = facultyService.addFaculty(faculty);
        return ResponseEntity.ok(tempFac);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable("id") long id) {
        Faculty tempFaculty = facultyService.findFaculty(id);
        return ResponseEntity.ok(tempFaculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty tempFaculty = facultyService.updateFaculty(faculty);
        return ResponseEntity.ok(tempFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable("id") long id) {
        Faculty tempFaculty = facultyService.deleteFaculty(id);
        if (tempFaculty == null) {
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
}
