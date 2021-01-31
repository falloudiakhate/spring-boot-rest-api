package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The Student Controller Class for mapping queries
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/v1/students")
public class StudentController {
    /**
     * The Student Class Service
     */
    @Autowired
    final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Get All Students
     * @return List of Students
     */
    @GetMapping()
    public ResponseEntity<List<Student>> getStudents(){
        return studentService.getStudents();
    }

    /**
     * Get One Student By id
     * @param id
     * @return One Student or null
     */
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Student> getOneStudent(@PathVariable(value = "id") Integer id){
        return studentService.getOneStudent(id);
    }

    /**
     * Get One Student by name
     * @param nom
     * @return One Student or null
     */
    @GetMapping(path = "/nom/{nom}")
    public ResponseEntity<Student> getStudentByNom(@PathVariable(value = "nom") String nom){
        return studentService.getStudentByNom(nom);
    }

    /**
     * Save One Student
     * @param student
     * @return
     */
    @PostMapping
    public  ResponseEntity<Student> saveStudents(@RequestBody Student student){
        return studentService.saveStudents(student);
    }

    /**
     * Delete One Student
     * @param id
     */
    @DeleteMapping(path = "/{id}")
    public void deleteStudent(@PathVariable(value = "id") Integer id){
        studentService.deleteStudentById(id);
    }

    /**
     * Update One Student
     * @param id
     * @param nom
     * @param prenom
     * @param email
     */
    @PutMapping(path = "/{id}")
    public void updateStudent(@PathVariable(value = "id")  Integer id, @RequestParam String nom,  @RequestParam String prenom, @RequestParam String email){
        studentService.updateStudentByID(id, nom, prenom, email);
    }

}
