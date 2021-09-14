package com.patika.hw5.controller;

import com.patika.hw5.dto.StudentDTO;
import com.patika.hw5.entity.Student;
import com.patika.hw5.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){this.studentService=studentService;}

    /**
     * @param studentId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudent(@PathVariable Long studentId){
        return new ResponseEntity<>(studentService.findById(studentId).get(), HttpStatus.OK) ;
    }

    /**
     * @return
     */
    @GetMapping("/all-students")
    public ResponseEntity<List<Student>> findAllStudent(){
        return new ResponseEntity<>(studentService.findAll(),HttpStatus.OK);
    }

    /**
     * @param student
     */
    @PostMapping("/save-student")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDTO student){
        Optional<Student> studentOptional=studentService.findById(student.getId());
        if (!studentOptional.isPresent())
            return new ResponseEntity<>(studentService.save(student).get(),HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    /**
     * @param id
     */
    @DeleteMapping("/delete-student")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        Optional<Student> studentOptional=studentService.findById(id);
        if (!studentOptional.isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        studentService.deleteById(id);
        return new ResponseEntity<>(studentOptional.get(),HttpStatus.ACCEPTED);
    }
    @PutMapping("/update-student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable int id){
        Optional<Student> studentOptional=studentService.findById(student.getId());
        if (!studentOptional.isPresent())  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(studentService.update(student),HttpStatus.ACCEPTED);
    }

}
