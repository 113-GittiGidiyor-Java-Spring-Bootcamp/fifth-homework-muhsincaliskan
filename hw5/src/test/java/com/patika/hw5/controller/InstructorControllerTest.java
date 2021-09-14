package com.patika.hw5.controller;
import com.patika.hw5.dto.CourseDTO;
import com.patika.hw5.dto.InstructorDTO;
import com.patika.hw5.dto.StudentDTO;
import com.patika.hw5.entity.Course;
import com.patika.hw5.entity.Instructor;
import com.patika.hw5.entity.Student;
import com.patika.hw5.exceptions.BadRequestException;
import com.patika.hw5.mappers.InstructorMapper;
import com.patika.hw5.mappers.StudentMapper;
import com.patika.hw5.repository.InstructorRepository;
import com.patika.hw5.service.InstructorService;
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
public class InstructorControllerTest {
    @Mock
    InstructorRepository mockInstructorRepository;
    @Mock
    InstructorService mockInstructorService;

    @InjectMocks
    InstructorController instructorController;

    @Test
    void saveInstructor(){
        Instructor instructor= new Instructor();
        instructor.setId(1111111111L);
        Optional<Instructor> expected=Optional.of(instructor);

        when(mockInstructorService.save(any())).thenReturn(expected);

        InstructorDTO instructorDTO= new InstructorDTO();
        Instructor actualInstructor= this.instructorController.save(instructorDTO).getBody();

        assertAll(
                ()-> assertNotNull(actualInstructor),
                ()-> assertEquals(expected.get(),actualInstructor),
                ()->assertEquals(instructor.getId(),actualInstructor.getId())

        );

    }
    @Test
    void saveInstructor2() {
        // given
        Course course = new Course();
        when(mockInstructorRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);

        // when
        InstructorDTO instructorDTO= new InstructorDTO();
        Executable executable =  () -> this.mockInstructorService.save(instructorDTO).get();

        // then
        assertThrows(BadRequestException.class, executable);
    }
}
