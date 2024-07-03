package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pets;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    PetService petService;
    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        List<Long> employeeIds = schedule.getEmployeesIds();
        List<Long> petIds = schedule.getPets();

        return new ScheduleDTO(schedule.getId(), employeeIds, petIds, schedule.getLocalDate(), schedule.getEmployeeSkills());

//        ScheduleDTO scheduleDTO=new ScheduleDTO();
//        scheduleDTO.setDate(schedule.getLocalDate());
//        scheduleDTO.setActivities(schedule.getEmployeeSkills());
//        scheduleDTO.setPetIds(schedule.getPets());
//        scheduleDTO.setEmployeeIds(schedule.getEmployeesIds());
//        scheduleDTO.setId(schedule.getId());
//        return  scheduleDTO;
    }

    private Schedule convertScheduleDTOtoSchedule(ScheduleDTO scheduleDTO)
    {
        return  new Schedule(scheduleDTO.getId(),scheduleDTO.getDate(),scheduleDTO.getEmployeeIds(),scheduleDTO.getPetIds(),scheduleDTO.getActivities());
    }


    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

//        Schedule schedule=convertScheduleDTOtoSchedule(scheduleDTO);
//        ScheduleDTO scheduleDTO1;
//
//        try
//        {
//            scheduleDTO1=convertScheduleToScheduleDTO(scheduleService.save1(schedule));
//        }
//        catch (Exception e)
//        {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule can not be saved", e);
//        }
//        return scheduleDTO1;
        Schedule schedule=new Schedule();
        schedule.setEmployeesIds(scheduleDTO.getEmployeeIds());
        schedule.setId(scheduleDTO.getId());
        schedule.setPets(scheduleDTO.getPetIds());
        schedule.setLocalDate(scheduleDTO.getDate());
        schedule.setEmployeeSkills(scheduleDTO.getActivities());
        return convertScheduleToScheduleDTO(scheduleService.save1(schedule));

    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO>scheduleDTOS;
        try
        {
            scheduleDTOS=scheduleService.getAllSchedules().stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule can not be fetched", e);
        }
        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedule;
        Pets pets=petService.findPetsById(petId);
        try {
            schedule=scheduleService.findScheduleByPet(pets);

        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule for Pet "+ petId+" can not be fetched", e);
        }
        return schedule.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {

        List<Schedule> schedule;
      try
      {
          schedule=scheduleService.findScheduleByEmployee(employeeId);

      }
      catch (Exception e)
      {
          throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Employee id"+employeeId+"not found",e);
      }
      return schedule.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());

    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {


        List<Schedule> schedule;
       try {
           schedule=scheduleService.findScheduleByEmployee(customerId);
       }
       catch (Exception e)
       {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Customer id"+customerId+"not found",e);
       }
       return schedule.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }
}
