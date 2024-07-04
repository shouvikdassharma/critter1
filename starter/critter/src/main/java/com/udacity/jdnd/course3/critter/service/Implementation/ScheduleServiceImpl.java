package com.udacity.jdnd.course3.critter.service.Implementation;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pets;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetsRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PetsRepository petsRepository;

    @Autowired
    CustomerRepository customerRepository;



    @Override
    public Schedule save1(Schedule schedule, List<Long> empIds, List<Long> petIds, Set<EmployeeSkill> employeeSkills, LocalDate localDate) {
        List<Pets> pets=petsRepository.findAllById(petIds);
        List<Employee> employees=employeeRepository.findAllById(empIds);
        schedule.setLocalDate(localDate);
        schedule.setEmployeeSkills(employeeSkills);


        schedule.setPets(pets);
        schedule.setEmployeesIds(employees);

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
