package com.example.demo.repository;

import com.example.demo.model.Batch;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findSubjectById(long id);
    List<Subject> findSubjectsByBatches(Batch batch);
    List<Subject> findSubjectsByUser(User user);
    Subject findSubjectsByName(String name);
}
