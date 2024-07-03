package com.udacity.jdnd.course3.critter.service.Implementation;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pets;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;



    @Override
    public Schedule save1(Schedule schedule,List<Long> empIds,List<Long> petIds) {

        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> findScheduleByPet(Pets pet) {
        return scheduleRepository.findByPets(pet);
    }

    @Override
    public List<Schedule> findScheduleByEmployee(Long employeeid) {
        return scheduleRepository.findByEmployeesIds(employeeid);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> findScheduleByCustomer(Long customerId) {
        Customer customer=customerRepository.getOne(customerId);
        return scheduleRepository.findByPetsIn(customer.getPets());
    }


}
