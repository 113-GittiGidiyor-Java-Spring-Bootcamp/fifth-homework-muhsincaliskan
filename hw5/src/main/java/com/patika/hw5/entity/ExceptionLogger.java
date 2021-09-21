package com.patika.hw5.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String exceptionType;
    private LocalDate exceptionThrownDate;
    private String exceptionMessage;
}
