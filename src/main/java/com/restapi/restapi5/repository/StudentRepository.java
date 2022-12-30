package com.restapi.restapi5.repository;

import com.restapi.restapi5.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

    boolean existsById(int id);

}
