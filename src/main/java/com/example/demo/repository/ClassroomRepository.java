package com.example.demo.repository;


import com.example.demo.model.Batch;
import com.example.demo.model.Classroom;
import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    List<Classroom> findClassroomBySubjectIdOrderByDateAsc(long id);

    List<Classroom> findClassroomsBySubjectOrderByDateAsc(Subject subject);

    List<Classroom> findClassroomsBySubjectAndDateOrderByDateAsc(Subject subject, String date);
}
