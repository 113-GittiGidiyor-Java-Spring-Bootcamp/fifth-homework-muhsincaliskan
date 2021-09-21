package com.patika.hw5.service;

import com.patika.hw5.dto.CourseDTO;
import com.patika.hw5.entity.Course;
import com.patika.hw5.entity.Instructor;
import com.patika.hw5.exceptions.CourseIsAlreadyExistException;
import com.patika.hw5.exceptions.StudentNumberForOneCourseExceededException;
import com.patika.hw5.mappers.CourseMapper;
import com.patika.hw5.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService implements BaseService<Course> {
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final CourseMapper courseMapper;
    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }


    /**
     * @param id
     * @return
     */
    @Transactional
    public Optional<Course> findById(Long id) {

        return  courseRepository.findById(id);
    }

    /**
     * @param object
     * @return
     */
    @Transactional
    public Optional<Course> save(CourseDTO object) {

        boolean isExists=  courseRepository.existsById(object.getId());

        if (isExists){
            throw new CourseIsAlreadyExistException("Course is already Exists");
        }
        Course course=courseMapper.maprFromCourseDTOtoCourse(object);
        return Optional.of(courseRepository.save(course));
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    /**
     * @param object
     * @return
     */
    @Override
    public Course update(Course object) {
        Optional<Course> courseOptional= courseRepository.findById(object.getId());

        if (courseOptional.isPresent()) {
            CourseDTO courseDTO= courseMapper.maprFromCoursetoCourseDTO(object);
            if (courseDTO.getStudents().size()>20)
                throw new StudentNumberForOneCourseExceededException("Max limit for class is 20");
            return courseRepository.save(object);
        }
        throw new RuntimeException("Not found.");
    }

    /**
     * @param id
     * @return
     */
    public Instructor findInstructorOfCourse(Long id) {

        return courseRepository.findById(id).get().getInstructor();

    }

}
