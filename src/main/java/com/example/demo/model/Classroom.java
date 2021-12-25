package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name =  "Classroom", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "class_name")
    String className;

    @Column(name = "start_time")
    String startTime;

    @Column(name = "end_time")
    String endTime;

    String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subject_id")
    Subject subject;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
