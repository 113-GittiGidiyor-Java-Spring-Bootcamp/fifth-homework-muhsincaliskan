package com.patika.hw5.repository;

import com.patika.hw5.entity.ExceptionLogger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionLoggerRepository extends PagingAndSortingRepository<ExceptionLogger,Long> {
}
