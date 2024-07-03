package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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




    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


    public Schedule(Long id, LocalDate localDate, List<Employee> employeesIds, List<Pets> pets, Set<EmployeeSkill> employeeSkills) {
        this.id = id;
        this.localDate = localDate;
        this.employeesIds = employeesIds;
        this.pets = pets;
        this.employeeSkills = employeeSkills;
    }

    public Schedule() {
    }

    public List<Employee> getEmployeesIds() {
        return employeesIds;
    }

    public void setEmployeesIds(List<Employee> employeesIds) {
        this.employeesIds = employeesIds;
    }

    public List<Pets> getPets() {
        return pets;
    }

    public void setPets(List<Pets> pets) {
        this.pets = pets;
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
    private List<Employee> employeesIds=new ArrayList<>();

    @ManyToMany(targetEntity = Pets.class)
    private List<Pets> pets=new ArrayList<>();

    @ElementCollection
    private Set<EmployeeSkill> employeeSkills;



}
