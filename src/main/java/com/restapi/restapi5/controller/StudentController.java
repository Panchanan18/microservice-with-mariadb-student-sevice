package com.restapi.restapi5.controller;

import com.restapi.restapi5.entity.Student;
import com.restapi.restapi5.entity.StudentGrades;
import com.restapi.restapi5.exception.StudentNotFoundException;
import com.restapi.restapi5.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import java.util.logging.Logger;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    private final Logger logger =LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/create")
    @ApiOperation(value = "Create and Save Student")
    public Student  saveStudent(@RequestBody Student student){
        logger.info("Added :"+ student.toString());
       return studentService.saveStudent(student);
    }
    @PostMapping("/createAll")
    @ApiOperation(value = "Create Multiple Students")

    public List<Student> saveStudent(@RequestBody List<Student> studentList){
        logger.info("All the students addded Successfully");
       return this.studentService.saveAllStudent(studentList);
    }
    @GetMapping("/getAllStudents")
    @ApiOperation(value = "Get All Students")

    public List<Student> getAllStudents(){
        logger.info("Getting all the students");
        return studentService.getAllStudents();
    }

    @GetMapping("/getById/{Id}")
    @ApiOperation(value = "Get Student By Id")

    public Student getStudentById(@PathVariable("Id") int studentId) throws StudentNotFoundException {
        logger.info("Student with id "+studentId+" is "+ studentService.getStudentById(studentId).toString());
        return studentService.getStudentById(studentId);
    }

    @DeleteMapping("/deleteById/{Id}")
    @ApiOperation(value = "Delete Student By Id")

    public Student deleteById(@PathVariable("Id") int studentId)throws  StudentNotFoundException{
        logger.info("Student with id "+studentId+" has been removed from database");
        return studentService.deleteById(studentId);
    }

    @PutMapping("/update/{Id}")
    @ApiOperation(value = "Update Student By Id")

    public Student updateStudent(@PathVariable("Id") int studentId, @RequestBody Student student) throws StudentNotFoundException {
       return studentService.updateStudent(studentId,student);
    }

    @GetMapping("/getAllGrades/{id}")
    public StudentGrades getAllGrades(@PathVariable int id){
        return studentService.getAllGrades(id);
    }






}
