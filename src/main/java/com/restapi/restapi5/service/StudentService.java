package com.restapi.restapi5.service;

import com.restapi.restapi5.entity.Student;
import com.restapi.restapi5.entity.StudentGrades;
import com.restapi.restapi5.entity.StudentResponse;
import com.restapi.restapi5.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    StudentResponse saveStudent(Student student);

    List<Student> saveAllStudent(List<Student> studentList);

    List<Student> getAllStudents();

    Student getStudentById(int studentId) throws StudentNotFoundException;

    StudentResponse deleteById(int studentId) throws StudentNotFoundException;

    StudentResponse updateStudent(int studentId, Student student) throws StudentNotFoundException;

    StudentGrades getAllGrades(int id);
}
