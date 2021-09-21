package com.patika.hw5.service;

import com.patika.hw5.dto.StudentDTO;
import com.patika.hw5.entity.Course;
import com.patika.hw5.entity.Student;
import com.patika.hw5.exceptions.StudentAgeNotValidException;
import com.patika.hw5.mappers.StudentMapper;
import com.patika.hw5.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class StudentService implements BaseService<Student> {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    /**
     * @return
     */
    public List findAll() {
        return (List) studentRepository.findAll();
    }
    public Optional<Student> findById(Long id) {
        return  studentRepository.findById(id);
    }

    /**
     * @param object
     * @return
     */
    public Optional<Student> save(StudentDTO object) {
        if ((LocalDate.now().getYear()-object.getBirthDate().getYear())<18||(LocalDate.now().getYear()-object.getBirthDate().getYear())>40){
            throw new StudentAgeNotValidException("Student is already Exists");
        }
        Student student=studentMapper.maprFromStudentDTOtoStudent(object);
        return Optional.of(studentRepository.save(student));
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
    @Override
    public Student update(Student object) {
        return studentRepository.save(object);
    }

    public List<Course> findCoursesOfStudent(Long id) {
        return studentRepository.findById(id).get().getStudentCourses();
    }
}
