package com.patika.hw5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitingResearchersDTO extends InstructorDTO{
    private double hourlySalary;
}
