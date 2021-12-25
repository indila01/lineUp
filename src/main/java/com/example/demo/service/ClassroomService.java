package com.example.demo.service;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Batch;
import com.example.demo.model.Classroom;
import com.example.demo.dto.ClassroomDto;
import com.example.demo.model.User;

import java.util.List;

public interface ClassroomService {
    Classroom createClassroom(ClassroomDto classroomDto);
    List<Classroom> getClassrooms();
    List<Classroom> getClassroomsBySubject(long id);

    List<Classroom> getClassroomsByBatch(Batch batch);

    List<Classroom> getClassroomsByUser(User user);

    void deleteClassroom(long id) throws CustomException;

    public List<Classroom> getClassroomsByBatchAndDate(Batch batch,String date);


    }
