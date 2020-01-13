package com.exercise.tema1.domain;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "company", nullable = true)
    private String company;

    @Column(name = "function", nullable = false)
    private String function;

    @Column(name = "hire_date", nullable = false)
    private Date hireDate;

//    public Set<Course> getCourses() {
//        return courses;
//    }

//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Course> courses = new HashSet<>();

    private static final long serialVersionUID=1L;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setHireDate(Date employee_date) {
        this.hireDate = employee_date;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getFunction() {
        return function;
    }

    public Date getHireDate() {
        return hireDate;
    }

    @Override
    public String toString() {
        return name + " " + function + " " + company + " " + hireDate;
    }
}

