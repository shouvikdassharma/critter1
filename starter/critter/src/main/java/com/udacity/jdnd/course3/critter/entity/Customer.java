package com.udacity.jdnd.course3.critter.entity;


import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private  Long id;

    public Customer(Long id, String name, String phoneNumber, String notes) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Pets> getPets() {
        return pets;
    }

    public void setPets(List<Pets> pets) {
        this.pets = pets;
    }

    @Nationalized
    private String name;
    private String phoneNumber;
    private String notes;
    
    @OneToMany(targetEntity = Pets.class)
    private List<Pets> pets;


}

