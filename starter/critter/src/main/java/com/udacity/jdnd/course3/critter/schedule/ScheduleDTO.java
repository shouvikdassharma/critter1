package com.udacity.jdnd.course3.critter.schedule;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds = new ArrayList<>();
    private List<Long> petIds = new ArrayList<>();
    private LocalDate date;
    private Set<EmployeeSkill> activities;
    public ScheduleDTO(long id, List<Long> employeeIds, List<Long> petIds, LocalDate date, Set<EmployeeSkill> activities) {
        this.id = id;
        this.employeeIds = (employeeIds != null) ? employeeIds : new ArrayList<>();
        this.petIds = (petIds != null) ? petIds : new ArrayList<>();
        this.date = date;
        this.activities = activities;
    }
    public ScheduleDTO() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public List<Long> getEmployeeIds() {
        return employeeIds;
    }
    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = (employeeIds != null) ? employeeIds : new ArrayList<>();
    }
    public List<Long> getPetIds() {
        return petIds;
    }
    public void setPetIds(List<Long> petIds) {
        this.petIds = (petIds != null) ? petIds : new ArrayList<>();
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Set<EmployeeSkill> getActivities() {
        return activities;
    }
    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}