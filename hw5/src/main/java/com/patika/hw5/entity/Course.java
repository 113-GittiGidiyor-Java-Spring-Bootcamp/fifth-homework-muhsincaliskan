package com.patika.hw5.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="course_name")
    private String courseName;

    @Column(name="course_code")
    private String courseCode;

    @Column(name="credit_score")
    private int creditScore;

    @ManyToMany(mappedBy = "studentCourses")
    private Set<Student> students = new HashSet<>();

    @ManyToOne
    private Instructor instructor;
}