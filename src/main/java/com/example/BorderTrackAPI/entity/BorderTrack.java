package com.example.BorderTrackAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class BorderTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Customs Office Code cannot be blank.")
    private String customsOfficeCode;

    @Column(nullable = false)
    @NotBlank(message = "Car Number cannot be blank.")
    private String carNumber;

    @Column(nullable = false)
    @NotNull(message = "Pass ID cannot be null.")
    private Integer passID;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime entryDate;
    private LocalDateTime exitDate;

    public enum Status {
        ENTERED,
        EXITED
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomsOfficeCode() {
        return customsOfficeCode;
    }

    public void setCustomsOfficeCode(String customsOfficeCode) {
        this.customsOfficeCode = customsOfficeCode;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getPassID() {
        return passID;
    }

    public void setPassID(Integer passID) {
        this.passID = passID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }
}
