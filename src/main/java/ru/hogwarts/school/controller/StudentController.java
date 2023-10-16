package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.SchoolService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final SchoolService schoolService;

    public StudentController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student tempStud = schoolService.createStudent(student);
        if (!verifierNotNull(tempStud)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(tempStud);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findStudent(@PathVariable("id") long id) {
        Student tempStud = schoolService.findStudent(id);
        if(!verifierNotNull(tempStud)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tempStud);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student tempStud = schoolService.updateStudent(student);
        if (!verifierNotNull(tempStud)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tempStud);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") long id) {
        Student tempStud = schoolService.deleteStudent(id);
        if (!verifierNotNull(tempStud)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tempStud);
    }

    @GetMapping("/get")
    public ResponseEntity<Collection<Student>> getStudentSetByAge(@RequestParam("age")
                                                                  int age) {
        Collection<Student> tempSet = schoolService.getStudentSetByAge(age);
        return ResponseEntity.ok(tempSet);
    }

    @GetMapping("/studentsAgeBetween")
    public Collection<Student> byAgeBetween(@RequestParam int min, @RequestParam int max) {
        return schoolService.filterByAgeBetween(min, max);
    }

    @GetMapping("/{id}/faculty")
    public Faculty studentsFaculty(@PathVariable("id") long studentId) {
        return schoolService.findFaculty(studentId);
    }
    private boolean verifierNotNull(Student student) {
        return student != null;
    }
}
