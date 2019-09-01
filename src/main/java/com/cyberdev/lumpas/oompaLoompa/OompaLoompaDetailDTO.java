package com.cyberdev.lumpas.oompaLoompa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class OompaLoompaDetailDTO {

    private String id;
    @NotBlank(message = "Name is required")
    private String name;
    @PositiveOrZero(message = "Please provide a valid age number")
    private Integer age;
    @NotBlank(message = "Job is required")
    private String job;
    @NotNull(message = "Height is required")
    private Float height;
    @NotNull(message = "Weight is required")
    private Float weight;
    private String description;

}
