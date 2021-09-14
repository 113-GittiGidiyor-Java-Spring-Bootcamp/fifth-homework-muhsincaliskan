package com.patika.hw5.service;

import com.patika.hw5.dto.InstructorDTO;
import com.patika.hw5.entity.Course;
import com.patika.hw5.entity.Instructor;
import com.patika.hw5.entity.PermanentInstructor;
import com.patika.hw5.exceptions.InstructorIsAlreadyExistException;
import com.patika.hw5.mappers.InstructorMapper;
import com.patika.hw5.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class InstructorService implements BaseService<Instructor> {
   private final InstructorRepository instructorRepository;
   private final InstructorMapper instructorMapper;

    @Override
    public List<Instructor> findAll() {
        return (List<Instructor>) instructorRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    public Optional<Instructor> findById(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if (instructor.isPresent()) {
            return instructorRepository.findById(id);
        }
        throw new RuntimeException("Not found");
    }

    /**
     * @param object
     * @return
     */
    public Optional<Instructor> save(InstructorDTO object) {
        boolean isExists=  instructorRepository.existsById(object.getId());

        if (isExists){
            throw new InstructorIsAlreadyExistException("Course is already Exists");
        }
        Instructor instructor=instructorMapper.maprFromInstructoDTOrtoInstructor(object);
        return Optional.of(instructorRepository.save(instructor));
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }

    /**
     * @param object
     * @return
     */
    @Override
    public Instructor update(Instructor object) {
        return instructorRepository.save(object);
    }

    /**
     * @param id
     * @return
     */
    public List<Course> findCoursesOfInstructor(Long id) {
        return  instructorRepository.findById(id).get().getCourseList();
    }
}
