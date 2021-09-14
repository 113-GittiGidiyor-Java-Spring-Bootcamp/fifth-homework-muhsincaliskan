package com.patika.hw5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@EqualsAndHashCode(callSuper = true)
@Table(name = "visiting_researchers")

public class VisitingResearches extends Instructor{
    @Column(name="hourly_salary")
    private Number hourlySalary;
}
