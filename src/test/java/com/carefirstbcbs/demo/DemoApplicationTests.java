package com.carefirstbcbs.demo;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.carefirstbcbs.demo.model.Employee;
import com.carefirstbcbs.demo.repo.EmployeeRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){
		Employee employee = new Employee(
			"Bill",
			"Girten",
			"bxg@usa.net",
			LocalDate.of(2000, Month.JANUARY, 25),
			"Chief Cook and Bottle Washer",
			"Phil",
			LocalDate.of(2024, Month.JUNE, 15)
		);
        employeeRepository.save(employee);
        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTest(){
        Employee employee = employeeRepository.findById(1L).get();
        Assertions.assertThat(employee.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfEmployeesTest(){
        List<Employee> employees = employeeRepository.findAll();
        Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest(){
        Employee employee = employeeRepository.findById(1L).get();
        employee.setEmailAddress("bxg@gmail.com");
        Employee employeeUpdated =  employeeRepository.save(employee);
        Assertions.assertThat(employeeUpdated.getEmailAddress()).isEqualTo("bxg@gmail.com");

    }
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){
        employeeRepository.deleteById(1L);
        Employee employee1 = null;
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeeByEmailAddress("bxg@gmail.com");
        if(optionalEmployee.isPresent()){
            employee1 = optionalEmployee.get();
        }
        Assertions.assertThat(employee1).isNull();
    }

}
