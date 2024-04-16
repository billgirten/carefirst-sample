package com.carefirstbcbs.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carefirstbcbs.demo.model.Employee;
import com.carefirstbcbs.demo.service.EmployeeService;




@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
    @GetMapping(path="{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("")
    public void createNewEmployee(@RequestBody Employee employee) {
        employeeService.addNewEmployee(employee);
    }

    @DeleteMapping(path="{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }

    @PutMapping(path="{employeeId}")
    public Employee updateEmployee(@PathVariable("employeeId") Long employeeId,
            @RequestBody Employee employeeDetails) {
                employeeService.updateEmployee(employeeId,
                                               employeeDetails.getFirstName(),
                                               employeeDetails.getLastName(),
                                               employeeDetails.getPhone(),
                                               employeeDetails.getBirthDate(),
                                               employeeDetails.getJobTitle(),
                                               employeeDetails.getDepartment(),
                                               employeeDetails.getLocation(),
                                               employeeDetails.getManagerReporting(),
                                               employeeDetails.getStartDate(), 
                                               employeeDetails.getManagerReporting());
                return employeeService.getEmployeeById(employeeId);
    }
    
}
