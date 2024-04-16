package com.carefirstbcbs.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carefirstbcbs.demo.model.Employee;
import com.carefirstbcbs.demo.repo.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

    public void addNewEmployee(Employee employee) {
		Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmailAddress(employee.getEmailAddress());
		if (employeeOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		Employee addedEmployee = employeeRepository.save(employee);
		System.out.println(addedEmployee);
	}

	public Employee getEmployeeById(Long employeeId) {
		boolean exists = employeeRepository.existsById(employeeId);
		if (!exists) {
			throw new IllegalStateException("employee with id " + employeeId + " does not exist");
		}
		return employeeRepository.findById(employeeId).get();
	}

	public void deleteEmployeeById(Long employeeId) {
		boolean exists = employeeRepository.existsById(employeeId);
		if (!exists) {
			throw new IllegalStateException("employee with id " + employeeId + " does not exist");
		}
		employeeRepository.deleteById(employeeId);
	}

	@Transactional
	public void updateEmployee(Long employeeId,
                               String firstName,
							   String lastName,
							   String phone,
							   LocalDate birthDate,
							   String jobTitle,
							   String department,
							   String emailAddress,
							   String location,
							   LocalDate startDate,
							   String managerReporting) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
			() -> new IllegalStateException("employee with id " + employeeId + " does not exist")
		);

		if (firstName != null && firstName.length() > 0 && !Objects.equals(employee.getFirstName(), firstName)) {
			employee.setFirstName(firstName);
		}

		if (lastName != null && lastName.length() > 0 && !Objects.equals(employee.getLastName(), lastName)) {
			employee.setLastName(lastName);
		}

		if (emailAddress != null && emailAddress.length() > 0 && !Objects.equals(employee.getEmailAddress(), emailAddress)) {
			Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmailAddress(emailAddress);
			if (employeeOptional.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			employee.setEmailAddress(emailAddress);
		}

        if (phone != null && phone.length() > 0 && !Objects.equals(employee.getPhone(), phone)) {
            employee.setPhone(phone);
        }

        if (birthDate != null && !Objects.equals(employee.getBirthDate(), birthDate)) {
            employee.setBirthDate(birthDate);
        }

        if (jobTitle != null && jobTitle.length() > 0 && !Objects.equals(employee.getJobTitle(), jobTitle)) {
            employee.setJobTitle(jobTitle);
        }

        if (department != null && department.length() > 0 && !Objects.equals(employee.getDepartment(), department)) {
            employee.setDepartment(department);
        }

		if (location != null && location.length() > 0 && !Objects.equals(employee.getLocation(), location)) {
			employee.setLocation(location);
		}

		if (startDate != null && !Objects.equals(employee.getStartDate(), startDate)) {
			employee.setStartDate(startDate);
		}

		if (managerReporting != null && managerReporting.length() > 0 && !Objects.equals(employee.getManagerReporting(), managerReporting)) {
			employee.setManagerReporting(managerReporting);
		}

	}
	
}
