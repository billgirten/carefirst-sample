package com.carefirstbcbs.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carefirstbcbs.demo.model.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.emailAddress = ?1")
    Optional<Employee> findEmployeeByEmailAddress(String emailAddress);

}
