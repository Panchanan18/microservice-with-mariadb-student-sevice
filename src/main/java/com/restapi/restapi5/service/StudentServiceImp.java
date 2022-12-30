package com.restapi.restapi5.service;

import com.restapi.restapi5.constants.KafkaConstants;
import com.restapi.restapi5.entity.Grade;
import com.restapi.restapi5.entity.Student;
import com.restapi.restapi5.entity.StudentGrades;
import com.restapi.restapi5.exception.StudentNotFoundException;
import com.restapi.restapi5.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImp implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private Logger logger= LoggerFactory.getLogger(StudentServiceImp.class);
    @Override
    public Student saveStudent(Student student) {
        kafkaTemplate.send(KafkaConstants.TOPIC,student);
        log.info("Message send to the student_update topic");
        log.info("Student with id "+student.getId()+" saved in the repository");
        return studentRepository.save(student);
    }

    @Override
    public List<Student> saveAllStudent(List<Student> studentList) {
        List<Student> students = studentRepository.saveAll(studentList);
        for(Student student: studentList){
            kafkaTemplate.send(KafkaConstants.TOPIC,student);
            log.info("Message send to the student_update topic");
            log.info("Student with id "+student.getId()+" saved in the repository");
        }
        return students;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int studentId) throws StudentNotFoundException {
       Optional<Student> student=studentRepository.findById(studentId);
       if(!student.isPresent()){
           throw new StudentNotFoundException("Student with id "+studentId+" does not exist");
       }
       return student.get();
    }

    @Override
    public Student deleteById(int studentId) throws StudentNotFoundException {
        Optional<Student> student=studentRepository.findById(studentId);
        if(!student.isPresent()){
            logger.info("Student with Id "+studentId+" does not exists");
            throw new StudentNotFoundException("Student with id "+studentId+" does not exist");
        }
        studentRepository.deleteById(studentId);
        logger.info("Student with id "+studentId+" has been removed from database");

        return student.get();
    }

    @Override
    public Student updateStudent(int studentId, Student student) throws StudentNotFoundException {
        Optional<Student> studentFromDb=studentRepository.findById(studentId);
        if(!studentFromDb.isPresent()){
            logger.info("Student with Id "+studentId+" does not exists");
            throw new StudentNotFoundException("Student with id "+studentId+" does not exist");
        }
//        studentRepository.deleteById(studentId);
        Student updatedStudent=studentRepository.findById(studentId).get();
//        updatedStudent.setStudentId(studentId);
        updatedStudent.setName(student.getName());
        updatedStudent.setAge(student.getAge());
        updatedStudent.setDepartment(student.getDepartment());
        updatedStudent.setGender(student.getGender());
        studentRepository.save(updatedStudent);
        return updatedStudent;
    }

    @Override
    public StudentGrades getAllGrades(int id) {
        Student student = studentRepository.findById(id).get();
        ResponseEntity<List<Grade>> responseEntity =
                restTemplate.exchange(
                        "http://localhost:8082/grades/getAllGrades/"+id,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Grade>>() {}
                );
        List<Grade> gradeList = responseEntity.getBody();
        StudentGrades studentGrades= new StudentGrades(student,gradeList);
        return studentGrades;

    }
}
