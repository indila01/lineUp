package com.example.demo.service;

import com.example.demo.MyUserDetails;
import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Batch;
import com.example.demo.model.User;
import com.example.demo.repository.BatchRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BatchRepository batchRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImplement(UserRepository userRepository, RoleRepository roleRepository, BatchRepository batchRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.batchRepository = batchRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        System.out.println(passwordEncoder.encode("indila01"));
    }

    @Override
    public User registerStudent(UserDto registrationDto) throws CustomException {


        User exist = userRepository.findUserByUsername(registrationDto.getUsername());

        if (!Objects.isNull(exist)) {
            throw new CustomException("Student already exists");
        }
        User student = new User();
        student.setFirstName(registrationDto.getFirstName());
        student.setLastName(registrationDto.getLastName());
        student.setEmail(registrationDto.getEmail());
        student.setUsername(registrationDto.getUsername());
        student.setPassword(passwordEncoder.encode(registrationDto.getUsername()));
        student.setRole(roleRepository.findRoleByName("STUDENT"));
        student.setBatch(batchRepository.findBatchByBatchCode(registrationDto.getBatch()));

        return userRepository.save(student);
    }


    @Override
    public User registerLecturer(UserDto registrationDto) throws CustomException {


        User exist = userRepository.findUserByUsername(registrationDto.getUsername());

        if (!Objects.isNull(exist)) {
            throw new CustomException("Student already exists");
        }
        User lecturer = new User();
        lecturer.setFirstName(registrationDto.getFirstName());
        lecturer.setLastName(registrationDto.getLastName());
        lecturer.setEmail(registrationDto.getEmail());
        lecturer.setUsername(registrationDto.getUsername());
        lecturer.setPassword(passwordEncoder.encode(registrationDto.getUsername()));
        lecturer.setRole(roleRepository.findRoleByName("LECTURER"));
        lecturer.setBatch(null);

        return userRepository.save(lecturer);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getStudents() {
        return userRepository.findAllByRole(roleRepository.findRoleByName("STUDENT"));
    }

    @Override
    public List<User> getLecturers() {
        return userRepository.findAllByRole(roleRepository.findRoleByName("LECTURER"));
    }


    @Override
    public User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void deleteUser(Long id) throws CustomException{
        userRepository.findById(id).orElseThrow(() -> new CustomException("User not found"));

        userRepository.deleteById(id);
    }

    @Override
    public void updateStudent(UserDto student) throws CustomException {
        User studentUpdate = userRepository.findUserByUsername(student.getUsername());

        if (Objects.isNull(studentUpdate)) {
            throw new CustomException("student does not exist");
        }

        Batch batch = batchRepository.findBatchByBatchCode(student.getBatch());
        if(Objects.isNull(batch)){
            throw new CustomException("Batch does not exist");
        }

        studentUpdate.setFirstName(student.getFirstName());
        studentUpdate.setLastName(student.getLastName());
        studentUpdate.setEmail(student.getEmail());
        studentUpdate.setUsername(student.getUsername());
        studentUpdate.setBatch(batch);

         userRepository.save(studentUpdate);
    }

    @Override
    public void changePassword(String username, String password) throws CustomException {

       User user = userRepository.findUserByUsername(username);

        if (Objects.isNull(user)) {
            throw new CustomException("User not found");
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public List<User> getStudentsByBatch(long batchId) {
        Batch batch = batchRepository.findBatchById(batchId);
        List<User> students = userRepository.findAllByBatch(batch);
        return students;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        User user = userRepository.findUserByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("Could not find user");
//        }
//
//        return new MyUserDetails(user);
//    }


}


