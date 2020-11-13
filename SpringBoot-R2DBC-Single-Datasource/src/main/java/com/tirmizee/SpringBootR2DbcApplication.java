package com.tirmizee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.tirmizee.entity.Employee;
import com.tirmizee.repository.EmployeeRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootR2DbcApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootR2DbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		EmployeeRepository employeeRepository = applicationContext.getBean(EmployeeRepository.class);
		Flux<Employee> flux = employeeRepository.findAll();
		Employee employee = flux.blockFirst();
		System.out.println(employee);
	}

}
