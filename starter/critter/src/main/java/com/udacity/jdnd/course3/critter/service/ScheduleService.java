package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pets;
import com.udacity.jdnd.course3.critter.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule save1(Schedule schedule);
    List<Schedule> findScheduleByPet(Pets pet);
    List<Schedule> findScheduleByEmployee(Long employeeId);

    List<Schedule> getAllSchedules();
    List<Schedule> findScheduleByCustomer(Long customerId);

}
