package com.patika.hw5.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermanentInstructorDTO extends InstructorDTO {
    @ApiModelProperty(example = "111111")
    @NotNull
    private double fixedSalary;
}
