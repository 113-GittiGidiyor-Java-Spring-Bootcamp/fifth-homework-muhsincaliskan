package com.patika.hw5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@SuperBuilder

@NoArgsConstructor
@Table(name = "permanent_instructors")
public class PermanentInstructor extends Instructor{
    @Column(name="fixed_salary")
    private Double fixedSalary;
}
