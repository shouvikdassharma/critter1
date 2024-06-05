package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee findEmployeeById(Long empid);
    void setEmployeeAvailability(Set<DayOfWeek> availability,Long id);


    List<Employee> findEmployeeByDateAndSkills(LocalDate localDate,Set<EmployeeSkill> employeeSkill);



}
