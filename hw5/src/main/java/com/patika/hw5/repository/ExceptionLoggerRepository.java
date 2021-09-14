package com.patika.hw5.repository;

import com.patika.hw5.entity.ExceptionLogger;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExceptionLoggerRepository extends PagingAndSortingRepository<ExceptionLogger,Long> {
}
