package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Table
@Entity
public class Employee {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Long getId() {
        return id;
    }

    public Employee(Long id, String name, Set<EmployeeSkill> employeeSkills, Set<DayOfWeek> dayOfWeeks) {
        this.id = id;
        this.name = name;
        this.employeeSkills = employeeSkills;
        this.dayOfWeeks = dayOfWeeks;
    }

    public Set<EmployeeSkill> getEmployeeSkills() {
        return employeeSkills;
    }

    public void setEmployeeSkills(Set<EmployeeSkill> employeeSkills) {
        this.employeeSkills = employeeSkills;
    }

    public Set<DayOfWeek> getDayOfWeeks() {
        return dayOfWeeks;
    }

    public void setDayOfWeeks(Set<DayOfWeek> dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long  id;

    private  String name;



    @ElementCollection
    Set<EmployeeSkill> employeeSkills;

    @ElementCollection
    Set<DayOfWeek> dayOfWeeks;
    




}
