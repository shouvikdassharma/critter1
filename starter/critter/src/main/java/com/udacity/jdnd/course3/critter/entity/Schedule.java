package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Table
@Entity
public class Schedule {

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
    @GeneratedValue
    private  Long id;
    private LocalDate localDate;

    public Schedule(Long id, LocalDate localDate, List<Employee> employees, List<Pets> pets, Set<EmployeeSkill> employeeSkills) {
        this.id = id;
        this.localDate = localDate;
        this.employees = employees;
        this.pets = pets;
        this.employeeSkills = employeeSkills;
    }

    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employees;

    @ManyToMany(targetEntity = Pets.class)
    private List<Pets> pets;

    @ElementCollection
    private Set<EmployeeSkill> employeeSkills;



}
