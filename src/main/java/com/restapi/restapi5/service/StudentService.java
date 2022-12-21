package com.restapi.restapi5.service;

import com.restapi.restapi5.entity.Student;
import com.restapi.restapi5.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);

    List<Student> saveAllStudent(List<Student> studentList);

    List<Student> getAllStudents();

    Student getStudentById(int studentId) throws StudentNotFoundException;

    Student deleteById(int studentId) throws StudentNotFoundException;

    Student updateStudent(int studentId, Student student) throws StudentNotFoundException;
}
