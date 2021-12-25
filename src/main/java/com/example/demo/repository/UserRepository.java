package com.example.demo.repository;

import com.example.demo.model.Batch;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    List<User> findAllByRole(Role role);

    User findUserByUsername(String username);

    List<User> findAllByBatch(Batch batch);






}
