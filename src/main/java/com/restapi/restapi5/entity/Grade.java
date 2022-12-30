package com.restapi.restapi5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    private int studentId;

    private String semester;
    private String  subject1Grade;
    private String  subject2Grade;
    private String  subject3Grade;
    private String  subject4Grade;
    private String  subject5Grade;

}
