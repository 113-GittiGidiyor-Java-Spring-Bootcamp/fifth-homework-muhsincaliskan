package com.patika.hw5.service;

import com.patika.hw5.dto.ExceptionLoggerDTO;
import com.patika.hw5.entity.ExceptionLogger;
import com.patika.hw5.exceptions.InstructorIsAlreadyExistException;
import com.patika.hw5.mappers.ExceptionLoggerMapper;
import com.patika.hw5.repository.ExceptionLoggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExceptionLoggerService implements BaseService<ExceptionLogger>{
    private final ExceptionLoggerRepository exceptionLoggerRepository;
    private final ExceptionLoggerMapper exceptionLoggerMapper;
    /**
     * @param object
     * @return
     */
    public Optional<ExceptionLogger> save(ExceptionLoggerDTO object) {
        boolean isExists=  exceptionLoggerRepository.existsById(object.getId());

        if (isExists){
            throw new InstructorIsAlreadyExistException("Course is already Exists");
        }
        ExceptionLogger exceptionLogger=exceptionLoggerMapper.mapFromExceptionLoggerDTOtoExceptionLogger(object);
        return Optional.of(exceptionLoggerRepository.save(exceptionLogger));
    }

    @Override
    public List<ExceptionLogger> findAll() {
        return (List<ExceptionLogger>) exceptionLoggerRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public ExceptionLogger update(ExceptionLogger object) {
        return null;
    }
}
