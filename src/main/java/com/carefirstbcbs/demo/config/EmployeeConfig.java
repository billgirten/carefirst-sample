package com.carefirstbcbs.demo.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carefirstbcbs.demo.model.Employee;
import com.carefirstbcbs.demo.repo.EmployeeRepository;

@Configuration
public class EmployeeConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            Employee Bill = new Employee(
				"Bill",
                "Girten",
				"bxg@usa.net",
				LocalDate.of(2000, Month.JANUARY, 25),
                "Chief Cook and Bottle Washer",
                "Phil",
                LocalDate.of(2024, Month.JUNE, 15)
			);
            Employee Phil = new Employee(
				"Phil",
                "Jones",
				"pxj@usa.net",
				LocalDate.of(2000, Month.JANUARY, 5),
                "Supervisor of Chief Cook and Bottle Washer",
                "Will",
                LocalDate.of(2024, Month.MAY, 15)
			);
            repository.saveAll(
                List.of(Bill, Phil)
            );
        };
    }

}
