package com.patika.hw5.controller;

import com.patika.hw5.dto.InstructorDTO;
import com.patika.hw5.dto.StudentDTO;
import com.patika.hw5.entity.Course;
import com.patika.hw5.entity.Student;
import com.patika.hw5.exceptions.BadRequestException;
import com.patika.hw5.mappers.StudentMapper;
import com.patika.hw5.repository.StudentRepository;
import com.patika.hw5.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    StudentRepository mockStudentRepository;
    @Mock
    StudentService mockStudentService;

    @InjectMocks
    StudentController studentController;

    @Test
    void saveStudent(){
        Student student= new Student();
        student.setId(1111111111L);
        Optional<Student> expected=Optional.of(student);

        when(mockStudentService.save(any())).thenReturn(expected);

        StudentDTO studentDTO= new StudentDTO();
        Student actualStudent= this.studentController.saveStudent(studentDTO).getBody();

        assertAll(
                ()-> assertNotNull(actualStudent),
                ()-> assertEquals(expected.get(),actualStudent),
                ()->assertEquals(student.getId(),actualStudent.getId())

        );

    }
    @Test
    void saveStudent2() {
        // given
        Student student = new Student();
        when(mockStudentRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);

        // when
        StudentDTO studentDTO= new StudentDTO();
        Executable executable =  () -> this.mockStudentService.save(studentDTO).get();

        // then
        assertThrows(BadRequestException.class, executable);
    }
}
