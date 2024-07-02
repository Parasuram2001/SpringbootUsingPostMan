package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	//Employee is the entity name
	//Long is type of primary key
    
}
