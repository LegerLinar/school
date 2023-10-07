package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student tempStud = schoolService.addStudent(student);
        return ResponseEntity.ok(tempStud);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findStudent(@PathVariable("id") long id) {
        Student tempStud = schoolService.findStudent(id);
        return ResponseEntity.ok(tempStud);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student tempStud = schoolService.updateStudent(student);
        return ResponseEntity.ok(tempStud);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") long id) {
        Student tempStud = schoolService.deleteStudent(id);
        return ResponseEntity.ok(tempStud);
    }

    @GetMapping("/get")
    public ResponseEntity<Collection<Student>> getStudentSetByAge(@RequestParam("age")
                                                                  int age) {
        Collection<Student> tempSet = schoolService.getStudentSetByAge(age);
        return ResponseEntity.ok(tempSet);
    }

}
