package com.patika.hw5.service;

import com.patika.hw5.controller.CourseController;
import com.patika.hw5.dto.CourseDTO;
import com.patika.hw5.entity.Course;
import com.patika.hw5.exceptions.BadRequestException;
import com.patika.hw5.repository.CourseRepository;
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
public class CourseServiceTest {
    @Mock
    CourseRepository mockCourseRepository;

    @Mock
    CourseService mockCourseService;

    @InjectMocks
    CourseController courseController;

    @Test
    void saveCourse(){
        Course course= new Course();
        course.setId(1111111111L);
        Optional<Course> expected=Optional.of(course);

        when(mockCourseService.save(any())).thenReturn(expected);

        CourseDTO courseDTO= new CourseDTO();
        Course actualCourse= this.courseController.saveCourse(courseDTO).getBody();

        assertAll(
                ()-> assertNotNull(actualCourse),
                ()-> assertEquals(expected.get(),actualCourse),
                ()->assertEquals(course.getId(),actualCourse.getId())

        );

    }
    @Test
    void saveCourse2() {
        // given
        Course course = new Course();
        when(mockCourseRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);

        // when
        CourseDTO dto = new CourseDTO();
        Executable executable =  () -> this.mockCourseService.save(dto).get();

        // then
        assertThrows(BadRequestException.class, executable);
    }
}
