package com.patika.hw5.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.patika.hw5.entity.Instructor;
import com.patika.hw5.entity.Student;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    @ApiModelProperty(example = "11111111111")
    @NotNull(message = "ID is mandatory")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long id;
    @ApiModelProperty(example = "Java Course")
    @NotBlank(message = "Course name is mandatory")
    private String courseName;


    @ApiModelProperty(example = "CourseCode")
    @NotBlank(message = "Course code is mandatory")
    private String courseCode;

    @ApiModelProperty(example = "60")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int creditScore;
    @JsonManagedReference
    @ManyToMany(mappedBy = "studentCourses")
    private Set<Student> students = new HashSet<>();

    @ManyToOne
    private Instructor instructor;
}
