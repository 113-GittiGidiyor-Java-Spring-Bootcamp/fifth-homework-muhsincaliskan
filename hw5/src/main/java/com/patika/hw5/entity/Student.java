package com.patika.hw5.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
@Builder
public class Student{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private  Long id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;
    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column(name="gender")
    private String Gender;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Course> studentCourses;
}