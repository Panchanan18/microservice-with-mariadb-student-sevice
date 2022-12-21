package com.restapi.restapi5.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student")

public class Student {

    @Id
    private int id;

    @NotBlank(message = "The name of the student can not be blank")
    @NotNull(message = "The name of the student can not be null")
    private String name;
    @NotBlank(message = "The name of the department can not be blank")
    @NotNull(message = "The name of the department can not be null")
    private String department;
    @Min(value=18,message = "The minimum age should be 18")
    @Positive(message = "Invalid Age")
    @Max(value=65, message = "Invalid age")
    private  int age;
    private  String gender;
}
