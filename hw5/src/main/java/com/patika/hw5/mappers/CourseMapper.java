package com.patika.hw5.mappers;

import com.patika.hw5.dto.CourseDTO;
import com.patika.hw5.entity.Course;
import com.patika.hw5.service.CourseService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Autowired
    CourseService courseService = null;
    Course maprFromCourseDTOtoCourse(CourseDTO courseDTO);
    CourseDTO maprFromCoursetoCourseDTO(Course course);
}
