package com.patika.hw5.service;

import com.patika.hw5.controller.InstructorController;
import com.patika.hw5.dto.InstructorDTO;
import com.patika.hw5.entity.Course;
import com.patika.hw5.entity.Instructor;
import com.patika.hw5.exceptions.BadRequestException;
import com.patika.hw5.repository.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {
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
        Instructor instructor = new Instructor();
        when(mockInstructorRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);

        // when
        InstructorDTO instructorDTO= new InstructorDTO();
        Executable executable =  () -> this.mockInstructorService.save(instructorDTO).get();

        // then
        assertThrows(BadRequestException.class, executable);
    }
}
