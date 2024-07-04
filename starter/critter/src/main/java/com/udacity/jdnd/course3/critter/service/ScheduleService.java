package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pets;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ScheduleService {

    List<Schedule> findScheduleByPet(Pets pet);
    List<Schedule> findScheduleByEmployee(Long employeeId);

    List<Schedule> getAllSchedules();
    List<Schedule> findScheduleByCustomer(Long customerId);



    Schedule save1(Schedule schedule, List<Long> employeeIds, List<Long> petIds, Set<EmployeeSkill> activities, LocalDate date);
}
