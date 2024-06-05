package com.udacity.jdnd.course3.critter.service.Implementation;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findEmployeeById(Long empid) {
        return employeeRepository.getOne(empid);
    }

    @Override
    public void setEmployeeAvailability(Set<DayOfWeek> availability, Long id) {
       Employee employee=employeeRepository.getOne(id);
       employee.setDayOfWeeks(availability);
       employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findEmployeeByDateAndSkills(LocalDate localDate, Set<EmployeeSkill> employeeSkill) {
        return employeeRepository.findEmployeeByDayOfWeek(localDate.getDayOfWeek()).stream().filter(employee -> employee.getEmployeeSkills().containsAll(employeeSkill)).collect(Collectors.toList());
    }


}
