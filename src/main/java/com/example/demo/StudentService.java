package com.example.demo;

import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;


    /**
     * Save a Student in The DB
     *
     * @param student
     * @return
     */
    public ResponseEntity<Student> saveStudents(Student student) {
        studentRepository.save(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        if(students.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    public ResponseEntity<Student> getOneStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Students with id "+id+" does not exist !")
        );
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    public ResponseEntity<Student> getStudentByNom(String nom) {
        Student student = studentRepository.findStudentByNom(nom).orElseThrow(
                () -> new ResourceNotFoundException("Student with name  " +nom+ " does not exit !")
        );
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Students with id "+id+" does not exist !")
        );
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @Transactional
    public void updateStudentByID(Integer id, String nom, String prenom, String email){
        Student student = studentRepository.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException("Student with id = " +id+ " does not exit !");
        });
        student.setNom(nom);
        student.setPrenom(prenom);
        student.setEmail(email);
    }
}