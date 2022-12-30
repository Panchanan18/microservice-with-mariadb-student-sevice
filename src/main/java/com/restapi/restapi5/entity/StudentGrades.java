package com.restapi.restapi5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGrades {
    private Student student;
    private List<Grade> gradeList;
}
