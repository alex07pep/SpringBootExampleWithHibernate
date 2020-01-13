package com.exercise.tema1.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "hours", nullable = true)
    private int hours;

    @Column(name = "value", nullable = true)
    private int value;

    @Column(name = "diploma", nullable = true)
    private boolean diploma;

    @Column(name = "year", nullable = true)
    private String year;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    public void setCourse_id(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDiploma(boolean diploma) {
        this.diploma = diploma;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getCourse_id() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public int getValue() {
        return value;
    }

    public boolean getDiploma() {
        return diploma;
    }

    public String getYear() {
        return year;
    }

    public Employee getEmployee() {
        return employee;
    }

    private static final long serialVersionUID=1L;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString(){
        return "Course: " + name + ", " + hours + "h "+ value+"$ passed:"+ diploma + " " + year + " - " + employee.getName();
    }
}
