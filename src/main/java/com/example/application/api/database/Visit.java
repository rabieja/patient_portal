package com.example.application.api.database;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
public class Visit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @NotEmpty
    private String date;
    private String doctor;
    @NotNull
    @NotEmpty
    private String status;
    private boolean reserved;
    private String patient;

    public Visit(String date, String doctor, String patient, boolean reserved) {
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.setReserved(reserved);
    }

    public Visit() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
        if(reserved == true){
            this.status = "reserved";
        }else {
            this.status = "free";
        }
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}
