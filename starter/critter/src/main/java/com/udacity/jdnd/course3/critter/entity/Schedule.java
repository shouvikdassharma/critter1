package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Table
@Entity
public class Schedule {


    public Schedule(LocalDate date, Set<EmployeeSkill> activities) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public Schedule(Long id, LocalDate localDate, List<Long> employeesIds, List<Long> pets, Set<EmployeeSkill> employeeSkills) {
        this.id = id;
        this.localDate = localDate;
        this.employeesIds = employeesIds;
        this.pets = pets;
        this.employeeSkills = employeeSkills;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<Long> getEmployeesIds() {
        return employeesIds;
    }

    public void setEmployeesIds(List<Long> employeesIds) {
        this.employeesIds = employeesIds;
    }

    public List<Long> getPets() {
        return pets;
    }

    public void setPets(List<Long> pets) {
        this.pets = pets;
    }

    public Schedule() {
    }

    public Set<EmployeeSkill> getEmployeeSkills() {
        return employeeSkills;
    }

    public void setEmployeeSkills(Set<EmployeeSkill> employeeSkills) {
        this.employeeSkills = employeeSkills;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private LocalDate localDate;


    @ManyToMany(targetEntity = Employee.class)
    private List<Long> employeesIds;

    @ManyToMany(targetEntity = Pets.class)
    private List<Long> pets;

    @ElementCollection
    private Set<EmployeeSkill> employeeSkills;



}
