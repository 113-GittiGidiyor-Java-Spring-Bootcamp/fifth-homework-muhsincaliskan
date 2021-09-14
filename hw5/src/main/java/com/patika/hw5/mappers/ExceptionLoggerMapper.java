package com.patika.hw5.mappers;

import com.patika.hw5.dto.ExceptionLoggerDTO;
import com.patika.hw5.entity.ExceptionLogger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExceptionLoggerMapper {


    ExceptionLogger mapFromExceptionLoggerDTOtoExceptionLogger(ExceptionLoggerDTO exceptionLoggerDTO);
    ExceptionLoggerDTO maprFromExceptionLoggertoExceptionLoggerDTO(ExceptionLogger exceptionLogger);
}
