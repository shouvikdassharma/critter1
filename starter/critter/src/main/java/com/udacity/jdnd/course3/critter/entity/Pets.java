package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
public class Pets {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Id
    @GeneratedValue
    private  Long id;

    public Pets(Long id, PetType petType, String name, Customer customer, LocalDate localDate, String notes) {
        this.id = id;
        this.petType = petType;
        this.name = name;
        this.customer = customer;
        this.localDate = localDate;
        this.notes = notes;
    }

    private PetType petType;

    @Nationalized
    private String name;

    @ManyToOne(targetEntity = Customer.class,optional = false)
    private Customer customer;
    private LocalDate localDate;
    private  String notes;



}
