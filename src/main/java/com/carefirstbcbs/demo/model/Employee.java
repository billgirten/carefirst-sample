package com.carefirstbcbs.demo.model;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.cglib.core.Local;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Employee {
    @Id
    @SequenceGenerator(
        name = "employee_sequence",
        sequenceName = "employee_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "employee_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phone;
    private LocalDate birthDate;
    private String jobTitle;
    private String department;
    private String location;
    private LocalDate startDate;
    private String managerReporting;

    @Transient
    private Integer age;

    public Employee() {}
    
    public Employee(Long id, String firstName, String lastName, String emailAddress, LocalDate birthDate,
            String jobTitle, String managerReporting) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.jobTitle = jobTitle;
        this.managerReporting = managerReporting;
    }
    
    public Employee(String firstName, String lastName, String emailAddress, LocalDate birthDate,
            String jobTitle, String managerReporting, LocalDate startDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.jobTitle = jobTitle;
        this.managerReporting = managerReporting;
        this.startDate = startDate;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getFirstName() {
        return firstName;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getEmailAddress() {
        return emailAddress;
    }



    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }



    public String getPhone() {
        return phone;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }



    public LocalDate getBirthDate() {
        return birthDate;
    }



    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }


    public String getJobTitle() {
        return jobTitle;
    }



    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }



    public String getDepartment() {
        return department;
    }



    public void setDepartment(String department) {
        this.department = department;
    }



    public String getLocation() {
        return location;
    }



    public void setLocation(String location) {
        this.location = location;
    }



    public LocalDate getStartDate() {
        return startDate;
    }



    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }



    public String getManagerReporting() {
        return managerReporting;
    }



    public void setManagerReporting(String managerReporting) {
        this.managerReporting = managerReporting;
    }



    public static Employee builder() {
        return new Employee();
    }

    

}
